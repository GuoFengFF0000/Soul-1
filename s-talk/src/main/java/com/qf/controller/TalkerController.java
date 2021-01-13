package com.qf.controller;

import com.qf.ws.ChatEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/websocket")
public class TalkerController {

    @Autowired
    ChatEndpoint chatEndpoint;

    @RequestMapping(value = "/onmessage")
    public String onmessage(@RequestBody Map map){
        chatEndpoint.onMessage((String)map.get("message"));
        return "发送成功";
    }


}
