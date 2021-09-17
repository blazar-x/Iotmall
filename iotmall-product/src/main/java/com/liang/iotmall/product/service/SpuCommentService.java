package com.liang.iotmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.common.utils.PageUtils;
import com.liang.iotmall.product.entity.SpuCommentEntity;

import java.util.Map;

/**
 * 商品评价
 *
 * @author liangpengfei
 * @email pengf.liang@foxmail.com
 * @date 2021-03-11 12:38:17
 */
public interface SpuCommentService extends IService<SpuCommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

