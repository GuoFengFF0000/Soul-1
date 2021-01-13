package com.qf.listener;

import com.alibaba.fastjson.JSONObject;
import com.qf.pojo.resp.BaseResp;
import com.qf.pojo.vo.Anchor;
import com.qf.service.AnchorService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class AnchorListener {

    @Autowired
    AnchorService anchorService;

    @RabbitListener(queues = "gift")
    public void gift(Anchor anchor, Channel channel, Message message) throws IOException {

        Integer aid = anchor.getId();
        Double total = anchor.getBalance();

        BaseResp byId = anchorService.findById(aid);

        Object o = JSONObject.toJSON(byId.getData());
        Anchor anchor1 = JSONObject.parseObject(o.toString(), Anchor.class);

        anchor1.setBalance(anchor1.getBalance() + total);

        BaseResp baseResp = anchorService.insertOrUpdate(anchor1);
        System.out.println(baseResp.getMessage());

    }
}
