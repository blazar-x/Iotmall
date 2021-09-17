package com.liang.iotmall.coupon.dao;

import com.liang.iotmall.coupon.entity.CouponHistoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券领取历史记录
 *
 * @author liangpengfei
 * @email pengf.liang@foxmail.com
 * @date 2021-03-14 18:15:54
 */
@Mapper
public interface CouponHistoryDao extends BaseMapper<CouponHistoryEntity> {

}
