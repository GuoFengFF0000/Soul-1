package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 *
 * 支付模块
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PayApplication {

    public static void main(String[] args) {

        SpringApplication.run(PayApplication.class);

    }

}
