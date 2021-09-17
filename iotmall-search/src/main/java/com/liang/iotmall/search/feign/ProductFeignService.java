package com.liang.iotmall.search.feign;

import com.liang.common.utils.R;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: Mr.Liang
 * @date: 2021/5/27 12:41
 * @Version: 1.0
 */
@FeignClient("iotmall-product")
public interface ProductFeignService {
        @GetMapping("/product/attr/info/{attrId}")
        R attrInfo(@PathVariable("attrId") Long attrId);
    }

