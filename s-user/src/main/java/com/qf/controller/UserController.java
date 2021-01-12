package com.qf.controller;

import com.qf.pojo.rep.UserRep;
import com.qf.pojo.resp.BaseResp;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public BaseResp login(@RequestBody UserRep userRep){
        return userService.login(userRep);
    }
    //注册
    @RequestMapping(value = "/registry",method = RequestMethod.POST)
    public BaseResp registry(@RequestBody UserRep UserReq){
        return userService.registry(UserReq);
    }
    //账号激活状态
    @RequestMapping("/editStatus/{id}")
    public BaseResp editStatus(@PathVariable("id")Integer id){
        return userService.editStatus(id);
    }


    //后台查所有
    @RequestMapping("/findAll")
    public BaseResp findAl(){
        return userService.findAll();
    }

    //根据Id查询
    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public BaseResp findById(@RequestBody Map map){
        return userService.findById(Integer.valueOf(map.get("id").toString()));
    }

    //随机查所有
    @RequestMapping("/selectAll")
    public  BaseResp selectAll(){
        return userService.selectAll();
    }

    //查彼此喜欢
    @RequestMapping("/selectById")
    public BaseResp selectById(@RequestBody Map map){
        return userService.selectById(Integer.valueOf(map.get("id").toString()));
    }

    //礼物打赏
    @RequestMapping(value = "/gift",method = RequestMethod.POST)
    public BaseResp gift(@RequestBody Map map){
        return userService.gift(map);
    }
}
