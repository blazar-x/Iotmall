package com.liang.iotmall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.common.to.SkureductionTo;
import com.liang.common.utils.PageUtils;
import com.liang.iotmall.coupon.entity.SkuFullReductionEntity;

import java.util.Map;

/**
 * 商品满减信息
 *
 * @author liangpengfei
 * @email pengf.liang@foxmail.com
 * @date 2021-03-14 18:15:54
 */
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSkuReduction(SkureductionTo skureductionTo);
}

