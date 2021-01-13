package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients
public class WebSocketApplication {

    public static void main(String[] args) {

        SpringApplication.run(WebSocketApplication.class);

    }

}
