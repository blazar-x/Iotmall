package com.liang.iotmall.search.vo;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.util.List;

/**
 * 封装页面所有可能传递过来的
 * @author: Mr.Liang
 * @date: 2021/5/26 17:37
 * @Version: 1.0
 */
@Data
public class SearchParam {
    private String keyword;  //页面
    private Long catalog3Id; //三级分类
    private String sort; //排序条件
    private Integer hasStock; //是否只显示有货
    private String  skuPrice; //加个区间查询
    private List<Long> brandId; //按照品牌id查询可以多选
    private List<String> attrs; //按照属性进行筛选
    private Integer pageNum=1;//按照页码进行查询
    /**
     * 原生的所有查询条件
     */
    private String _queryString;
}
