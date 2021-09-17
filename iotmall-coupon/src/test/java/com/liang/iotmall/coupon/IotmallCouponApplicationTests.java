package com.liang.iotmall.coupon;

import com.liang.iotmall.coupon.service.CouponService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IotmallCouponApplicationTests {

    @Autowired
    CouponService couponService;

    @Test
    void contextLoads() {

    }

}
