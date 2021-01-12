package com.qf.listener;

import com.qf.pojo.resp.BaseResp;
import com.qf.pojo.vo.Anchor;
import com.qf.service.AnchorService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AnchorListener {

    @Autowired
    AnchorService anchorService;

    @RabbitListener(queues = "gift")
    public void gift(Anchor anchor, Channel channel, Message message) throws IOException {

        BaseResp baseResp = anchorService.insertOrUpdate(anchor);

        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
