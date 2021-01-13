package com.qf.service.WebSocketServiceImpl;

import com.qf.client.UserClient;
import com.qf.pojo.resp.BaseResp;
import com.qf.service.WebSocketService;
import com.qf.utils.CookieUtils;
import com.qf.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class WebSocketServiceImpl implements WebSocketService {

    @Autowired
    JWTUtils jwtUtils;

    @Autowired
    CookieUtils cookieUtils;

    @Autowired
    UserClient userClient;

    @Override
    public BaseResp getToken(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();

        String token = cookieUtils.getToken(cookies);

        Map verify = jwtUtils.Verify(token);

        BaseResp baseResp = userClient.findById(verify);

        return baseResp;

    }
}