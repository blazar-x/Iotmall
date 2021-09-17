package com.liang.iotmall.product.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: Mr.Liang
 * @date: 2021/5/28 7:56
 * @Version: 1.0
 */
@ConfigurationProperties(prefix = "iotmall.thread")
@Component
@Data
public class ThreadPoolConfigProperties {
    private Integer coreSize;
    private Integer maxSize;
    private Integer keepAliveTime;
}
