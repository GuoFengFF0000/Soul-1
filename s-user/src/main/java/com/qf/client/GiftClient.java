package com.qf.client;

import com.qf.pojo.resp.BaseResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "s-gift",fallback = GiftFallback.class)
public interface GiftClient {

    @RequestMapping(value = "/gift/findAll",method = RequestMethod.GET)
    public BaseResp findAll();

    @RequestMapping(value = "/gift/findById",method = RequestMethod.GET)
    public BaseResp findById(@RequestParam("id")Integer id);
}
