package com.qf.controller;

import com.qf.pojo.resp.BaseResp;
import com.qf.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/like")
public class LikeController {

    @Autowired
    LikeService likeService;

    @RequestMapping("/findRandom")
    public BaseResp findRandom(@RequestBody Map map){
        return likeService.findRandom(map);
    }

}
