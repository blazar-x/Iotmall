package com.liang.iotmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.common.utils.PageUtils;
import com.liang.iotmall.product.entity.SpuInfoDescEntity;

import java.util.List;
import java.util.Map;

/**
 * spu信息介绍
 *
 * @author liangpengfei
 * @email pengf.liang@foxmail.com
 * @date 2021-03-11 12:38:17
 */
public interface SpuInfoDescService extends IService<SpuInfoDescEntity> {

    void saveSpuInfoDesc(SpuInfoDescEntity descEntity);

    PageUtils queryPage(Map<String, Object> params);
}

