package com.liang.iotmall.product.vo;

import lombok.Data;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;

/**
 * @author: Mr.Liang
 * @date: 2021/5/27 19:21
 * @Version: 1.0
 */
@Data
public class SkuItemSaleAttrVo {
    private Long attrId;

    private String attrName;

    private List<AttrValueWithSkuIdVO> attrValues;
//    private String attrValues;

}

