package com.liang.iotmall.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: Mr.Liang
 * @date: 2021/5/19 19:35
 * @Version: 1.0
 */
@Data
public class MergeVo {
    private Long purchaseId; //整单id
    private List<Long> items; //合并项id
}
