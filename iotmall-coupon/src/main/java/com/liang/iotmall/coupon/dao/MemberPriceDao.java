package com.liang.iotmall.coupon.dao;

import com.liang.iotmall.coupon.entity.MemberPriceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品会员价格
 *
 * @author liangpengfei
 * @email pengf.liang@foxmail.com
 * @date 2021-03-14 18:15:54
 */
@Mapper
public interface MemberPriceDao extends BaseMapper<MemberPriceEntity> {

}
