package com.liang.iotmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.common.utils.PageUtils;
import com.liang.iotmall.order.entity.OrderSettingEntity;

import java.util.Map;

/**
 * 订单配置信息
 *
 * @author liangpengfei
 * @email pengf.liang@foxmail.com
 * @date 2021-03-15 12:33:35
 */
public interface OrderSettingService extends IService<OrderSettingEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

