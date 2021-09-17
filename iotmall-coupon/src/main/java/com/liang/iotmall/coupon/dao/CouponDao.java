package com.liang.iotmall.coupon.dao;

import com.liang.iotmall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 *
 * @author liangpengfei
 * @email pengf.liang@foxmail.com
 * @date 2021-03-14 18:15:54
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {

}
