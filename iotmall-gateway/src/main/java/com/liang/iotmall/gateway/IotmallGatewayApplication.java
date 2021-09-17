package com.liang.iotmall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//排除跟数据源相关的类的加载
public class IotmallGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotmallGatewayApplication.class, args);
    }

}
