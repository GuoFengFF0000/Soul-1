package com.qf.controller;

import com.qf.pojo.resp.BaseResp;
import com.qf.service.LoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * baiYu
 */
@RestController
@RequestMapping("/like")
@CrossOrigin
public class LoveController {

    @Autowired
    LoveService likeService;

    @RequestMapping("/findRandom")
    public BaseResp findRandom(HttpServletRequest req) {
        return likeService.findRandom(req);
    }

    @RequestMapping(value = "/like",method = RequestMethod.POST)
    public BaseResp like(HttpServletRequest req, @RequestBody Map map){
        return likeService.like(req,map);
    }


    @RequestMapping(value = "/noLike",method = RequestMethod.POST)
    public BaseResp noLike(HttpServletRequest req, @RequestBody Map map){
        return likeService.noLike(req,map);
    }
}
