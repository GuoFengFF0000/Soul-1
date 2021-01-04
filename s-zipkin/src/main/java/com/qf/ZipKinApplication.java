package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin2.server.internal.EnableZipkinServer;

/**
 *
 * Zipkin 链路跟踪
 * @EnableDiscoveryClient 注册客户端
 * @EnableZipkinServer 链路跟踪服务端
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer
public class ZipKinApplication {

    public static void main(String[] args) {

        SpringApplication.run(ZipKinApplication.class);

    }

}
