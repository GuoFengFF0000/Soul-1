package com.qf.controller;

import com.qf.pojo.resp.BaseResp;
import com.qf.ws.TalkerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/talker")
public class TalkerController {

    @Autowired
    private TalkerEndpoint talkerEndpoint;

    @RequestMapping("/sendOneWebSocketOneToOne/{id}/{message}")
    public BaseResp sendOneWebSocketOneToOne(@PathVariable("id") String userId, @PathVariable("message") String message) {
        BaseResp baseResp = new BaseResp();
        try {
            talkerEndpoint.sendOneMessage(userId, message);
            baseResp.setCode(200);
            baseResp.setMessage("发送成功");
        } catch (Exception e) {
            baseResp.setCode(301);
            baseResp.setMessage("发送出错");
        }


        return baseResp;
    }


}
