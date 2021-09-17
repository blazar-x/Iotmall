package com.liang.iotmall.order.dao;

import com.liang.iotmall.order.entity.RefundInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 退款信息
 *
 * @author liangpengfei
 * @email pengf.liang@foxmail.com
 * @date 2021-03-15 12:33:35
 */
@Mapper
public interface RefundInfoDao extends BaseMapper<RefundInfoEntity> {

}
