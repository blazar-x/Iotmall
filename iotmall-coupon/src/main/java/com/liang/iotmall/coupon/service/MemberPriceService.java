package com.liang.iotmall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.common.utils.PageUtils;
import com.liang.iotmall.coupon.entity.MemberPriceEntity;

import java.util.Map;

/**
 * 商品会员价格
 *
 * @author liangpengfei
 * @email pengf.liang@foxmail.com
 * @date 2021-03-14 18:15:54
 */
public interface MemberPriceService extends IService<MemberPriceEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

