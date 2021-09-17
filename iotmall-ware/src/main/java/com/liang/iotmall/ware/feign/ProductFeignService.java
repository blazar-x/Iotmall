package com.liang.iotmall.ware.feign;

import com.liang.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: Mr.Liang
 * @date: 2021/5/20 14:15
 * @Version: 1.0
 */
//1)让所有请求过网关  /api/product/skuinfo/info/{skuId}
//2）直接让后台指定服务处理 /product/skuinfo/info/{skuId}
@FeignClient("iotmall-product")
public interface ProductFeignService {
    @RequestMapping("/product/skuinfo/info/{skuId}")
    public R info(@PathVariable("skuId") Long skuId);

}
