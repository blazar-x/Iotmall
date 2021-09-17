package com.liang.iotmall.order.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: Mr.Liang
 * @date: 2021/6/2 10:24
 * @Version: 1.0
 */
@Controller
public class OrderWebController {

    @GetMapping("/toTrade")
    public String toTrade(){
        return "confirm";
    }
}
