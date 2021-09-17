package com.liang.iotmall.iotmall.thirdparty.controller;

import com.liang.common.utils.R;
import com.liang.iotmall.iotmall.thirdparty.component.SmsComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Mr.Liang
 * @date: 2021/5/28 12:15
 * @Version: 1.0
 */
@RestController
@RequestMapping("/sms")
public class SmsSendController {
    @Autowired
    SmsComponent smsComponent;

    /**
     * 提供给别的服务进行调用
     * @param phone: 
     * @param code:
     * @return: com.liang.common.utils.R
     */
    @GetMapping("/sendcode")
    public R sendCode(@RequestParam("phone")String phone,@RequestParam("code") String code){
        smsComponent.sendSmsCode(phone,code);
        return R.ok();
    }
}
