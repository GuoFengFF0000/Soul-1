package com.qf.client;

import com.qf.pojo.resp.BaseResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name = "s-user")
public interface UserClient {

    @RequestMapping("/user/findAll")
    public BaseResp findAll();

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public BaseResp findById(@RequestBody Map map);
}
