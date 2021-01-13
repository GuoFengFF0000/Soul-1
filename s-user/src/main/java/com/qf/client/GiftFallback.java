package com.qf.client;

import com.qf.pojo.resp.BaseResp;
import org.springframework.stereotype.Component;

@Component
public class GiftFallback implements GiftClient {
    @Override
    public BaseResp findAll() {
        return null;
    }

    @Override
    public BaseResp findById(Integer id) {
        return null;
    }
}
