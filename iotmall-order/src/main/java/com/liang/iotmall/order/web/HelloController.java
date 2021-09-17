package com.liang.iotmall.order.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: Mr.Liang
 * @date: 2021/6/2 9:34
 * @Version: 1.0
 */
@Controller
public class HelloController {

    @GetMapping("/{page}.html")
    public String listPage(@PathVariable("page")String page){
        return page;
    }
}
