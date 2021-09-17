package com.liang.iotmall.search.controller;

import com.liang.iotmall.search.service.MallSearchService;
import com.liang.iotmall.search.vo.SearchParam;
import com.liang.iotmall.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Mr.Liang
 * @date: 2021/5/26 16:59
 * @Version: 1.0
 */
@Controller
public class SearchController {

    @Autowired
    MallSearchService mallSearchService;

    @GetMapping("/list.html")
    public String listPage(SearchParam param, Model model, HttpServletRequest request){
        param.set_queryString(request.getQueryString());
        SearchResult result= mallSearchService.search(param);
        model.addAttribute("result",result);
        return "list";
    }
}
