package com.qf.service.impl;

import com.qf.dao.UserRepository;
import com.qf.pojo.rep.UserRep;
import com.qf.pojo.resp.BaseResp;
import com.qf.pojo.vo.User;
import com.qf.service.UserService;
import com.qf.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public BaseResp login(UserRep userRep) {
        BaseResp baseResp = new BaseResp();
        //获取用户名
        String userName = userRep.getUserName();
        User byUserName = userRepository.findByUserName(userName);
        if(byUserName==null){
            baseResp.setCode(404);
            baseResp.setMessage("找不到该用户");
            return baseResp;
        }
        if(!byUserName.getPassword().equals(userRep.getPassword())){
            baseResp.setCode(500);
            baseResp.setMessage("密码不对");
            return baseResp;
        }
        //使用jwt加密
        JWTUtils jwtUtils = new JWTUtils();
        //将个人信息放置在jwt中
        Map map = new HashMap<>();
        map.put("userName",byUserName.getUserName());
        map.put("id",byUserName.getId());
        String token = jwtUtils.token(map);
        baseResp.setData(token);
        baseResp.setMessage("登陆成功");
        baseResp.setCode(200);
        return baseResp;
    }



    @Override
    public BaseResp findAll() {
        BaseResp baseResp = new BaseResp();
        List<User> all=userRepository.findAll();
        baseResp.setCode(200);
        baseResp.setMessage("查询所有成功");
        baseResp.setData(all);
        return baseResp;
    }

    @Override
    public BaseResp findById(Integer id) {
        BaseResp baseResp = new BaseResp();
        Optional<User> byId = userRepository.findById(id);
        if(byId.isPresent()){
            baseResp.setCode(200);
            baseResp.setData(byId.get());
            baseResp.setMessage("查询一个成功");
            return baseResp;
        }
        baseResp.setCode(201);
        baseResp.setMessage("查询一个失败");
        return baseResp;
    }
}
