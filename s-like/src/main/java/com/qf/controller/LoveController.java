package com.qf.controller;

import com.qf.pojo.resp.BaseResp;
import com.qf.pojo.vo.User;
import com.qf.service.LoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * baiYu
 */
@RestController
@RequestMapping("/love")
//@CrossOrigin
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

    @RequestMapping(value = "/findFriend",method = RequestMethod.POST)
    public List<User> findFriend(HttpServletRequest request){
        return likeService.findFriend(request);
    }
}
