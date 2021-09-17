package com.liang.iotmall.member.feign;

import com.liang.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: Mr.Liang
 * @date: 2021/3/17 13:52
 * @Version: 1.0
 */
@FeignClient("iotmall-coupon")
public interface CouponFeignService {
    @RequestMapping("/coupon/coupon/member/list")
    public R membercoupons();
}
