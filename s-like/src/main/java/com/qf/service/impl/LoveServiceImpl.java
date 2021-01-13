package com.qf.service.impl;

import com.qf.client.UserClient;
import com.qf.dao.LoveRepository;
import com.qf.pojo.resp.BaseResp;
import com.qf.pojo.vo.Love;
import com.qf.pojo.vo.User;
import com.qf.service.LoveService;
import com.qf.utils.CookieUtils;
import com.qf.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoveServiceImpl implements LoveService {

    @Autowired
    UserClient userClient;

    @Autowired
    LoveRepository likeRepository;

    private CookieUtils cookieUtils = new CookieUtils();

    @Override
    public BaseResp findRandom(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        String token = cookieUtils.getToken(cookies);
        JWTUtils jwtUtils = new JWTUtils();
        Map verify = jwtUtils.Verify(token);
        Integer id = (Integer) verify.get("id");

        List<User> userList = new ArrayList<>();

        List<User> hobbyList = new ArrayList<>();

        List<User> noLikeList = new ArrayList<>();

        //喜欢自己的
        List<Love> byLikeId = likeRepository.findByLikeId(id);

        for (Love love : byLikeId) {
            if (love.getSta() == null){
                Map map = new HashMap();
                map.put("id",love.getUserId());
                BaseResp byId = userClient.findById(map);
                userList.add((User)byId.getData());
            }
            //好友
            if (love.getSta() == "yes"){
                Map map = new HashMap();
                map.put("id",love.getUserId());
                BaseResp byId = userClient.findById(map);
                hobbyList.add((User)byId.getData());
            }
        }

        //自己不喜欢的
        List<Love> byUserId = likeRepository.findByUserId(id);
        for (Love love : byUserId) {
            if (love.getSta() == "no"){
                Map map = new HashMap();
                map.put("id",love.getLikeId());
                BaseResp byId = userClient.findById(map);
                noLikeList.add((User)byId.getData());
            }
            if (love.getSta() == "yes"){
                Map map = new HashMap();
                map.put("id",love.getLikeId());
                BaseResp byId = userClient.findById(map);
                hobbyList.add((User)byId.getData());
            }
        }

        //查所有
        List<User> list = userClient.selectAll();
        for (User o : list) {
            for (User user : userList) {
                if (o.getId() == user.getId()){
                    list.remove(o);
                }
            }

            for (User user : noLikeList) {
                if (o.getId() == user.getId()){
                    list.remove(o);
                }
            }

            for (User user : hobbyList) {
                if (o.getId() == user.getId()){
                    list.remove(o);
                }
            }
            if (o.getId() == id){
                list.remove(o);
            }

            userList.add(o);
        }
        BaseResp baseResp = new BaseResp();
        baseResp.setCode(200);
        baseResp.setData(userList);
        baseResp.setMessage("查询成功");
        baseResp.setCount(Long.valueOf(userList.size()));
        return baseResp;


    }

    @Override
    public BaseResp like(HttpServletRequest req, Map map) {
        BaseResp baseResp = new BaseResp();
        Integer likeId = (Integer) map.get("id");
        Cookie[] cookies = req.getCookies();
        String token = cookieUtils.getToken(cookies);
        JWTUtils jwtUtils = new JWTUtils();
        Map verify = jwtUtils.Verify(token);
        Integer id = (Integer) verify.get("id");
        Love like = likeRepository.findByLikeIdAndUserId(id, likeId);
        if (like == null){
            Love like1 = new Love();
            like1.setLikeId(likeId);
            like1.setUserId(id);
            likeRepository.saveAndFlush(like1);
            baseResp.setCode(200);
            baseResp.setMessage("喜欢成功");
            return baseResp;
        } else {
            like.setSta("yes");
            likeRepository.saveAndFlush(like);
            baseResp.setCode(200);
            baseResp.setMessage("互相喜欢");
            baseResp.setData(like);
            return baseResp;
        }

    }

    @Override
    public BaseResp noLike(HttpServletRequest req, Map map) {
        BaseResp baseResp = new BaseResp();
        Integer likeId = (Integer) map.get("id");
        Cookie[] cookies = req.getCookies();
        String token = cookieUtils.getToken(cookies);
        JWTUtils jwtUtils = new JWTUtils();
        Map verify = jwtUtils.Verify(token);
        Integer id = (Integer) verify.get("id");
        Love like = likeRepository.findByLikeIdAndUserId(id, likeId);
        if (like == null){
            Love love = new Love();
            love.setUserId(id);
            love.setLikeId(likeId);
            love.setSta("no");
            likeRepository.saveAndFlush(love);
            baseResp.setCode(200);
            baseResp.setMessage("不喜欢成功");
            return baseResp;
        } else {
            like.setSta("no");
            likeRepository.saveAndFlush(like);
            baseResp.setCode(200);
            baseResp.setMessage("不喜欢成功");
            baseResp.setData(like);
            return baseResp;
        }

    }
}
