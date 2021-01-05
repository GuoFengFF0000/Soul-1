package com.qf.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "s-user")
public interface UserClient {



}
