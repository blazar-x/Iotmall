package com.liang.iotmall.product.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * @author: Mr.Liang
 * @date: 2021/5/26 11:35
 * @Version: 1.0
 */
@Configuration
public class MyRedissonConfig {

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() throws IOException {
        // 1、创建配置
        Config config = new Config();
        // Redis url should start with redis:// or rediss://
        config.useSingleServer().setAddress("redis://192.168.255.129:6379");
        // 2、根据 Config 创建出 RedissonClient 实例
        return Redisson.create(config);
    }
    }
