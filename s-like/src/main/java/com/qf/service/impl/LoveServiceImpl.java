package com.qf.service.impl;

import com.alibaba.fastjson.JSONObject;
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
        BaseResp baseResp = new BaseResp();
        Cookie[] cookies = req.getCookies();
        String token = cookieUtils.getToken(cookies);
        JWTUtils jwtUtils = new JWTUtils();
        Map verify = jwtUtils.Verify(token);
        Integer id = (Integer) verify.get("id");

        List<User> noShow = new ArrayList<>();

        //喜欢自己的
        List<Love> byLikeIdAndSta = likeRepository.findByLikeIdAndSta(id, null);

        if (byLikeIdAndSta.size() != 0) {
            baseResp.setCode(200);
            baseResp.setData(byLikeIdAndSta.get(0));
            baseResp.setMessage("查询成功");
            return baseResp;
        }

        List<Love> yes = likeRepository.findByLikeIdAndSta(id, "yes");
        List<Love> byUserId = likeRepository.findByUserId(id);
        List<Love> byLikeId = likeRepository.findByLikeId(id);


        for (Love love : byLikeId) {
            if ("no".equals(love.getSta())) {
                Map map = new HashMap();
                map.put("id", love.getUserId());
                BaseResp data = userClient.findById(map);
                Object data1 = data.getData();
                User user = JSONObject.parseObject(JSONObject.toJSON(data1).toString(), User.class);
                noShow.add(user);
            }
        }

        for (Love love : yes) {
            Map map = new HashMap();
            map.put("id", love.getUserId());
            BaseResp data = userClient.findById(map);
            Object data1 = data.getData();
            User user = JSONObject.parseObject(JSONObject.toJSON(data1).toString(), User.class);
            noShow.add(user);
        }

        for (Love love : byUserId) {
            Map map = new HashMap();
            map.put("id", love.getLikeId());
            BaseResp data = userClient.findById(map);
            Object data1 = data.getData();
            User user = JSONObject.parseObject(JSONObject.toJSON(data1).toString(), User.class);
            noShow.add(user);
        }
        if (true) {
            Map map = new HashMap();
            map.put("id", id);
            BaseResp byId = userClient.findById(map);
            Object data = byId.getData();
            User user = JSONObject.parseObject(JSONObject.toJSON(data).toString(), User.class);
            noShow.add(user);
        }

        User user1 = userClient.selectIdRandom();
        user1 = getUser(user1, noShow);
        baseResp.setCode(200);
        baseResp.setData(user1);
        baseResp.setMessage("查询成功");
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
        if (like == null) {
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
            baseResp.setCode(201);
            baseResp.setMessage("互相喜欢成功");
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
        if (like == null) {
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

    public User getUser(User user1, List<User> noShow) {
        for (User user : noShow) {
            boolean b = user1.getId() == user.getId();
            if (b) {
                user1 = userClient.selectIdRandom();
                getUser(user1, noShow);
            }
        }
        return user1;
    }

}
