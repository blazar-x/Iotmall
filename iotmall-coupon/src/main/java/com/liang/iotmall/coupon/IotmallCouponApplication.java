package com.liang.iotmall.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class IotmallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotmallCouponApplication.class, args);
    }

}
