package com.qf.controller;

import com.qf.pojo.CircleFriends;
import com.qf.pojo.resp.BaseResp;
import com.qf.service.CircleFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/circleFriend")
public class CircleFriendController {

    @Autowired
    CircleFriendService circleFriendService;

    @RequestMapping("/insertComment")
    public BaseResp insertComment(@RequestBody CircleFriends circleFriends){

        return circleFriendService.insertComment(circleFriends);

    }

    @RequestMapping("/circleFriendFindAllByNew")
    public BaseResp circleFriendFindAllByNew(){

        return circleFriendService.circleFriendFindAllByNew();
    }

    @RequestMapping("/circleFriendFindAllByNewByZanNums")
    public BaseResp circleFriendFindAllByNewByZanNums(){
        return circleFriendService.circleFriendFindAllByNewByZanNums();
    }

    @RequestMapping("/addZanNums")
    public BaseResp addZanNums(@RequestParam("cfId") Integer cfId){
        return circleFriendService.addZanNums(cfId);
    }
    /**
     * 后台crud
     */
    @RequestMapping("/adminFriendAdd")
    public BaseResp adminFriendAdd(@RequestBody CircleFriends circleFriends){
        return circleFriendService.adminFriendAdd(circleFriends);
    }

    @RequestMapping("/adminFriendDel")
    public BaseResp adminFriendDel(@RequestBody Map map){
        return circleFriendService.adminFriendDel(Integer.valueOf(map.get("cfId").toString()));
    }

    @RequestMapping("/adminFriendUpdate")
    public BaseResp adminFriendUpdate(@RequestBody CircleFriends circleFriends){
        return circleFriendService.adminFriendUpdate(circleFriends);
    }
    @RequestMapping("/adminFriendFindAll")
    public BaseResp adminFriendFindAll(){
        return circleFriendService.adminFriendFindAll();
    }

    @RequestMapping("/adminFriendFindByCfId")
    public BaseResp adminFriendFindByCfId(@RequestBody Map map){
        return circleFriendService.adminFriendFindByCfId(Integer.valueOf(map.get("cfId").toString()));
    }
}
