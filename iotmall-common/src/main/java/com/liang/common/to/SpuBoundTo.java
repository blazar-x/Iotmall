package com.liang.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: Mr.Liang
 * @date: 2021/5/14 11:06
 * @Version: 1.0
 */
@Data
public class SpuBoundTo {
    private Long spuId;
    private BigDecimal buyBounds;
    private BigDecimal growBounds;
}
