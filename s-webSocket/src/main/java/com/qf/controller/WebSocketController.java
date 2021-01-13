package com.qf.controller;

import com.qf.pojo.resp.BaseResp;
import com.qf.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class WebSocketController {

    @Autowired
    WebSocketService webSocketService;

    @RequestMapping("/token")
    public BaseResp getToken(HttpServletRequest request) {

        return webSocketService.getToken(request);

    }

}
