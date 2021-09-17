package com.liang.iotmall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.common.utils.PageUtils;
import com.liang.iotmall.coupon.entity.CouponSpuRelationEntity;

import java.util.Map;

/**
 * 优惠券与产品关联
 *
 * @author liangpengfei
 * @email pengf.liang@foxmail.com
 * @date 2021-03-14 18:15:54
 */
public interface CouponSpuRelationService extends IService<CouponSpuRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

