package com.liang.common.to;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: Mr.Liang
 * @date: 2021/5/14 15:13
 * @Version: 1.0
 */
@Data
public class SkureductionTo {

    private Long skuId;
    private int fullCount;
    private BigDecimal discount;
    private int countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private int priceStatus;
    private List<MemberPrice> memberPrice;
}
