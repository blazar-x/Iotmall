package com.liang.iotmall.product.feign;

import com.liang.common.to.SkureductionTo;
import com.liang.common.utils.R;
import com.liang.common.to.SpuBoundTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: Mr.Liang
 * @date: 2021/5/14 10:44
 * @Version: 1.0
 */
@FeignClient("iotmall-coupon")
public interface CouponFeignService {
    /**
     * 只要json模型数据是兼容的.双方服务无需使用同一个to
     *
     * @param boundTo:
     * @return: com.liang.common.utils.R
     */
    @PostMapping("coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundTo boundTo);

    @PostMapping("coupon/skufullreduction/saveinfo")
    R saveSkuReduction(@RequestBody SkureductionTo skureductionTo);
}
