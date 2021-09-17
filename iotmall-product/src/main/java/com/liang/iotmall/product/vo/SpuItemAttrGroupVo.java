package com.liang.iotmall.product.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author: Mr.Liang
 * @date: 2021/5/27 19:29
 * @Version: 1.0
 */
@ToString
@Data
public class SpuItemAttrGroupVo {

    private String groupName;

    private List<SpuBaseAttrVo> attrs;
}
