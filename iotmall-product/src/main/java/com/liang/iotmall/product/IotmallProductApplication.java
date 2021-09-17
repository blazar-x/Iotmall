package com.liang.iotmall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.redisson.spring.session.config.EnableRedissonHttpSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.math.BigDecimal;


@EnableRedisHttpSession
@EnableCaching
@EnableFeignClients(basePackages = "com.liang.iotmall.product.feign")
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.liang.iotmall.product.dao")
public class IotmallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotmallProductApplication.class, args);
    }
    
}
