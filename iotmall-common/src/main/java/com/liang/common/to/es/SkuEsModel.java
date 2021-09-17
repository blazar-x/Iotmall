package com.liang.common.to.es;

import com.sun.scenario.effect.impl.prism.PrImage;
import jdk.internal.util.xml.impl.Attrs;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: Mr.Liang
 * @date: 2021/5/24 9:30
 * @Version: 1.0
 */
@Data
public class SkuEsModel {

    private Long skuId;
    private Long spuId;
    private String skuTitle;
    private BigDecimal skuPrice;
    private String skuImg;
    private Long saleCount;
    private Boolean hasStock;
    private Long hotScore;
    private Long brandId;
    private Long catalogId;
    private String brandName;
    private String brandImg;
    private String catelogName;
    private List<Attrs> attrs;

    @Data
    public static class Attrs{
        private Long attrId;
        private String attrName;
        private String attrValue;

    }



}
