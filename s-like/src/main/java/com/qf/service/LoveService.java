package com.qf.service;

import com.qf.pojo.resp.BaseResp;
import com.qf.pojo.vo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface LoveService {
    BaseResp findRandom(HttpServletRequest req);

    BaseResp like(HttpServletRequest req, Map map);

    BaseResp noLike(HttpServletRequest req, Map map);

    List<User> findFriend(Map map);
}
