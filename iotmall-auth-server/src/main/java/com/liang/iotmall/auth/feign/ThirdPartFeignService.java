package com.liang.iotmall.auth.feign;

import com.liang.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: Mr.Liang
 * @date: 2021/5/28 12:48
 * @Version: 1.0
 */

@Service
@FeignClient("iotmall-third-party")
public interface ThirdPartFeignService {

    @GetMapping("/sms/sendcode")
    R sendCode(@RequestParam("phone")String phone,@RequestParam("code") String code);
}
