package com.qf.controller;

import com.qf.pojo.rep.UserRep;
import com.qf.pojo.resp.BaseResp;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public BaseResp login(@RequestBody UserRep userRep){
        return userService.login(userRep);
    }


    //查所有
    @RequestMapping("/findAll")
    public BaseResp findAll(){
        return userService.findAll();
    }

    //根据Id查询
    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public BaseResp findById(@RequestBody Map map){
        return userService.findById(Integer.valueOf(map.get("id").toString()));
    }
}
