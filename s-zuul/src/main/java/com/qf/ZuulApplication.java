package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 *
 * Zuul网关配置类
 * @EnableDiscoveryClient 注册eureka
 * @EnableZuulProxy 开启网关
 *
 */
@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulApplication {

    public static void main(String[] args) {

        SpringApplication.run(ZuulApplication.class);

    }

}
