package com.liang.iotmall.product.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.liang.iotmall.product.entity.AttrEntity;
import lombok.Data;

import java.util.List;

/**
 * @author: Mr.Liang
 * @date: 2021/4/29 21:12
 * @Version: 1.0
 */
@Data
public class AttrGroupWithAttrsVo {

    /**
     * 分组id
     */
    @TableId
    private Long attrGroupId;
    /**
     * 组名
     */
    private String attrGroupName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 描述
     */
    private String descript;
    /**
     * 组图标
     */
    private String icon;
    /**
     * 所属分类id
     */
    private Long catelogId;
    /**
     * 当前分组下的所有属性
     */
    private List<AttrEntity> attrs;
}
