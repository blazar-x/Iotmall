package com.liang.iotmall.product.web;

import com.liang.iotmall.product.entity.CategoryEntity;
import com.liang.iotmall.product.service.CategoryService;
import com.liang.iotmall.product.vo.Catelog2Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.Map;

/**
 * @author: Mr.Liang
 * @date: 2021/5/24 17:34
 * @Version: 1.0
 */
@Controller
public class IndexController {
    @Autowired
    CategoryService categoryService;

    @GetMapping({"/","/index.html"})
    public String indexPage(Model model){
        // TODO: 2021/5/24  查出所有的一级分类
      List<CategoryEntity> categoryEntities= categoryService.getLevel1Categorys();

        //视图解析器进行拼串
        //classpath:templates/+返回值+.html
        model.addAttribute("categorys",categoryEntities);
        return "index";
    }

//    index/catalog.json
    @ResponseBody
    @GetMapping("index/catalog.json")
    public Map<String,List<Catelog2Vo>> getCatelogJson(){

        return categoryService.getCatelogJson();
    }

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}
