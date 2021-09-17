package com.liang.iotmall.ware.vo;

import lombok.Data;

/**
 * @author: Mr.Liang
 * @date: 2021/5/20 11:47
 * @Version: 1.0
 */
@Data
public class PurchaseItemDoneVo {
    private Long ItemId;
    private Integer status;
    private String reason;

}
