package com.liang.iotmall.ware.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: Mr.Liang
 * @date: 2021/5/20 11:46
 * @Version: 1.0
 */
@Data
public class PurchaseDoneVo {
    @NotNull
    private Long id;//采购单id

    private List<PurchaseItemDoneVo> items;

}
