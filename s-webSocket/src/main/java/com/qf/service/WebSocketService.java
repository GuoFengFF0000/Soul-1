package com.qf.service;

import com.qf.pojo.resp.BaseResp;

import javax.servlet.http.HttpServletRequest;

public interface WebSocketService {

    BaseResp getToken(HttpServletRequest request);

}
