package com.qf.service.impl;
import com.qf.dao.UserMapper;
import com.qf.dao.UserRepository;
import com.qf.pojo.rep.UserRep;
import com.qf.pojo.resp.BaseResp;
import com.qf.pojo.vo.User;
import com.qf.service.UserService;
import com.qf.utils.CookieUtils;
import com.qf.utils.JWTUtils;
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
    public BaseResp findById(Integer id) {
        BaseResp baseResp = new BaseResp();
        User user = userRepository.findById(id).get();
        if(user != null){
            baseResp.setCode(200);
            baseResp.setData(user);
            baseResp.setMessage("查询一个成功");
            return baseResp;
        }
        baseResp.setCode(201);
        baseResp.setMessage("查询一个失败");
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
    public User selectIdRandom() {
        User user = userMapper.selectIdRandom();
        return user;
    }



}
