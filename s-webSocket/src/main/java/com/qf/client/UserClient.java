package com.qf.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.qf.pojo.resp.BaseResp;

import java.util.Map;

@FeignClient(name = "s-user")
public interface UserClient {

    @RequestMapping(value = "/user/findById",method = RequestMethod.POST)
    BaseResp findById(@RequestBody Map map);

}
