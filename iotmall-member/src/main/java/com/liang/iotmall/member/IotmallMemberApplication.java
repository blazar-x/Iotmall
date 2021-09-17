package com.liang.iotmall.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.liang.iotmall.member.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class IotmallMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotmallMemberApplication.class, args);
    }

}
