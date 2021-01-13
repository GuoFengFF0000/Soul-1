package com.qf;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 朋友圈模块
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CircleFriendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CircleFriendApplication.class);
    }
}
