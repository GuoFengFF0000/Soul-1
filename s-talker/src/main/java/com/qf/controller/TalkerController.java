package com.qf.controller;

import com.qf.pojo.resp.BaseResp;
import com.qf.ws.TalkerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/talker")
public class TalkerController {

    @Autowired
    private TalkerEndpoint talkerEndpoint;

    @RequestMapping(value = "/sendOneWebSocketOneToOne/{userId}/{message}/{id}",method = RequestMethod.POST)
    public BaseResp sendOneWebSocketOneToOne(@PathVariable("userId") String userId, @PathVariable("message") String message,@PathVariable("id")String id) {
        BaseResp baseResp = new BaseResp();
        try {
            talkerEndpoint.sendOneMessage(userId, message,id);
            baseResp.setCode(200);
            baseResp.setMessage("发送成功");
        } catch (Exception e) {
            baseResp.setCode(301);
            baseResp.setMessage("发送出错");
        }


        return baseResp;
    }


}
