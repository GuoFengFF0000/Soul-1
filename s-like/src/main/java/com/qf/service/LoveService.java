package com.qf.service;

import com.qf.pojo.resp.BaseResp;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface LoveService {
    BaseResp findRandom(HttpServletRequest req);

    BaseResp like(HttpServletRequest req, Map map);

    BaseResp noLike(HttpServletRequest req, Map map);
}
