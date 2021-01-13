package com.qf.controller;

import com.qf.pojo.rep.Message;
import com.qf.ws.ChatEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/talk")
public class TalkController {

    @Autowired
    ChatEndpoint chatEndpoint;

    @RequestMapping("/send")
    public String send(@RequestBody Map map){
        chatEndpoint.onMessage((String)map.get("message"));
        return "发送成功";
    }

}
