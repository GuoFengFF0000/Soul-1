package com.qf.service.impl;
import com.alibaba.fastjson.JSONObject;
import com.qf.client.GiftClient;
import com.qf.dao.UserMapper;
import com.qf.dao.UserRepository;
import com.qf.pojo.rep.UserRep;
import com.qf.pojo.resp.BaseResp;
import com.qf.pojo.vo.Anchor;
import com.qf.pojo.vo.Gift;
import com.qf.pojo.vo.User;
import com.qf.service.UserService;
import com.qf.utils.CookieUtils;
import com.qf.utils.JWTUtils;
import com.qf.utils.RedisUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;



    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    UserMapper userMapper;

    @Autowired
    GiftClient giftClient;

    @Autowired
    RedisUtils redisUtils;

    @Override
    public BaseResp login(UserRep userRep) {
        BaseResp baseResp = new BaseResp();
        String email1 = userRep.getEmail();
        User byEmail1 = userRepository.findByEmail(email1);
        if(byEmail1.getStatus()==0){
            baseResp.setCode(401);
            baseResp.setMessage("请先到邮箱激活账号");
            return baseResp;
        }else {
            //获取邮箱
            String email = userRep.getEmail();
            User byEmail = userRepository.findByEmail(email);
            if (byEmail == null) {
                baseResp.setCode(404);
                baseResp.setMessage("未找到该用户");
                return baseResp;
            } else if (!byEmail.getPassword().equals(userRep.getPassword())) {
                baseResp.setCode(500);
                baseResp.setMessage("密码错误");
                return baseResp;
            }
            //使用JWT
            JWTUtils jwtUtils = new JWTUtils();
            Map map = new HashMap();
            map.put("email", byEmail.getEmail());
            map.put("id", byEmail.getId());

            String token = jwtUtils.token(map);
            baseResp.setCode(200);
            baseResp.setMessage("登录成功");
            baseResp.setData(token);
            return baseResp;
        }

    }



    @Override
    public BaseResp findAll() {

        BaseResp baseResp = new BaseResp();
        List<User> all = userRepository.findAll();
        baseResp.setCode(200);
        baseResp.setMessage("查询所有成功");
        baseResp.setData(all);

        return baseResp;
    }

    @Override
    public BaseResp findById(HttpServletRequest request) {
        //先解析登录后jwt产生的token 解密获得用户id
        BaseResp baseResp = new BaseResp();
        Cookie[] cookies = request.getCookies();
        CookieUtils cookieUtils = new CookieUtils();
        String token = cookieUtils.getToken(cookies);
        JWTUtils jwtUtils = new JWTUtils();
        Map verify = jwtUtils.Verify(token);
        Integer id1 = (Integer)verify.get("id");
        Optional<User> byId = userRepository.findById(id1);
        if(byId.isPresent()){
            baseResp.setCode(200);
            baseResp.setMessage("查询一个ok");
            baseResp.setData(byId.get());

        }
        return baseResp;
    }

    @Override
    public BaseResp registry(UserRep userReq) {
        //1.将用户信息存储到数据库中，将用户的id以及email地址获取，发送给rabbitmq
        BaseResp baseResp = new BaseResp();
        if(userReq.getEmail()==null){
            baseResp.setMessage("邮箱不能为空");
            baseResp.setCode(101);
            return baseResp;
        }
        User byUserName = userRepository.findByUserName(userReq.getUserName());
        if(byUserName!=null){
            baseResp.setMessage("用户名已存在");
            baseResp.setCode(102);
            return baseResp;
        }
        User byEmail = userRepository.findByEmail(userReq.getEmail());
        if(byEmail!=null){
            baseResp.setMessage("邮箱已经被注册");
            baseResp.setCode(103);
            return baseResp;
        }
        //复制对象
        User user = new User();
        BeanUtils.copyProperties(userReq,user);
        user.setStatus(0);
        User user1 = userRepository.saveAndFlush(user);
        Map map = new HashMap<>();
        map.put("id",user1.getId());
        map.put("email",user1.getEmail());
        rabbitTemplate.convertAndSend("","send-mail",map);
        baseResp.setCode(200);
        baseResp.setMessage("注册成功,请到邮箱激活账号");
        return baseResp;
    }

    @Override
    public BaseResp editStatus(Integer id) {
        BaseResp baseResp = new BaseResp();
        Optional<User> byId = userRepository.findById(id);
        if(!byId.isPresent()){
            baseResp.setCode(103);
            baseResp.setMessage("未找到该用户");
            return baseResp;
        }
        User user = byId.get();
        if(user.getStatus()!=null&&user.getStatus()==1){
            baseResp.setMessage("该账号已经激活,无法重复");
            return baseResp;
        }
        user.setStatus(1);
        userRepository.saveAndFlush(user);
        baseResp.setCode(200);
        baseResp.setMessage("激活成功");
        return baseResp;
    }

    @Override
    public List<User> selectAll() {
        BaseResp baseResp = new BaseResp();
        List<User> users = userMapper.selectAll();
        baseResp.setCode(200);
        baseResp.setMessage("随机查询所有成功");
        baseResp.setData(users);

        return users;
    }

    @Override
    public BaseResp selectById(Integer id) {
        BaseResp baseResp = new BaseResp();
        List<User> user = userMapper.selectById(id);
        if(user!=null){
            baseResp.setCode(200);
            baseResp.setMessage("查询一个成功");
            baseResp.setData(user);
            return baseResp;
        }
        baseResp.setCode(201);
        baseResp.setMessage("查询失败");
        return baseResp;
    }


    @Override
    public BaseResp gift(Map map) {
        BaseResp baseResp = new BaseResp();

        int uid = Integer.valueOf(String.valueOf(map.get("uid")));
        int aid = Integer.valueOf(String.valueOf(map.get("aid")));;
        int gid = Integer.valueOf(String.valueOf(map.get("gid")));;
        int num = Integer.valueOf(String.valueOf(map.get("num")));;

        Optional<User> userById = userRepository.findById(uid);

        Gift gift=null;
        BaseResp giftById = giftClient.findById(gid);
        if (giftById.getCode()==200){
            Object data = giftById.getData();
            Object o = JSONObject.toJSON(data);
            gift = JSONObject.parseObject(o.toString(), Gift.class);
        }

        double total = gift.getPrice() * num;

        if (userById.isPresent() && gift!=null){
            User user = userById.get();
            if (user.getBalance()==null||user.getBalance() < total){
                baseResp.setCode(300);
                baseResp.setMessage("余额不足!");
                return baseResp;
            }else {
                user.setBalance(user.getBalance() - total);
                User user1 = userRepository.saveAndFlush(user);

                Anchor anchor = new Anchor();
                anchor.setId(aid);
                anchor.setBalance(total);
                rabbitTemplate.convertAndSend("gift",anchor);

                Double aDouble = redisUtils.ZScore(String.valueOf(aid), user1.getUserName());
                if (aDouble==null){
                    redisUtils.ZSet(String.valueOf(aid),user1.getUserName(),total);
                    System.out.println(redisUtils.ZScore(String.valueOf(aid), user1.getUserName()));
                    System.out.println(redisUtils.ZRevRangeWithScores(String.valueOf(aid),0,-1).toArray().toString());
                }else {
                    redisUtils.ZIncrScore(String.valueOf(aid),user1.getUserName(),total);
                    System.out.println(redisUtils.ZScore(String.valueOf(aid), user1.getUserName()));
                    System.out.println(redisUtils.ZRevRangeWithScores(String.valueOf(aid),0,-1).toArray().toString());
                }

                baseResp.setCode(200);
                baseResp.setMessage(user1.getUserName()+"送了"+num+"个"+gift.getName());
                baseResp.setData(user1);
                return baseResp;
            }
        }else {
            baseResp.setCode(300);
            baseResp.setMessage("用户不存在");
            return baseResp;
        }
    }

    @Override
    public BaseResp saveOrUpdate(User user) {
        BaseResp baseResp = new BaseResp();
        User user1 = userRepository.saveAndFlush(user);
        baseResp.setCode(200);
        baseResp.setMessage("个人信息已完善");
        return baseResp;
    }


}
