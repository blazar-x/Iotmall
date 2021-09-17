package com.liang.iotmall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @author: Mr.Liang
 * @date: 2021/3/25 17:03
 * @Version: 1.0
 */
@Configuration
public class IotmallCorsConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter() {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //头部
        corsConfiguration.addAllowedMethod("*");
        //请求方法
        corsConfiguration.addAllowedOrigin("*");
        //来源
        corsConfiguration.setAllowCredentials(true);
        //设置允许带cookie跨域
        corsConfiguration.addAllowedHeader("*");

        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsWebFilter(source);
    }


}
