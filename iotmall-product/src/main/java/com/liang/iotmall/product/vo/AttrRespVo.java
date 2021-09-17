package com.liang.iotmall.product.vo;

import com.liang.iotmall.product.dao.AttrAttrgroupRelationDao;
import lombok.Data;

/**
 * 响应数据
 *
 * @author: Mr.Liang
 * @date: 2021/4/27 12:13
 * @Version: 1.0
 */
@Data
public class AttrRespVo extends AttrVo {
    /*
     * "catelogName": "手机/数码/手机", //所属分类名字
     * 	"groupName": "主体", //所属分组名字
     */
    private String catelogName;

    private String groupName;

    private Long[] catelogPath;
}
