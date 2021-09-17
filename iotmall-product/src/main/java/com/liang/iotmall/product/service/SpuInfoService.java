package com.liang.iotmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.common.utils.PageUtils;
import com.liang.iotmall.product.entity.SpuInfoEntity;
import com.liang.iotmall.product.vo.SpuSaveVo;

import java.util.Map;

/**
 * spu信息
 *
 * @author liangpengfei
 * @email pengf.liang@foxmail.com
 * @date 2021-03-11 12:38:17
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuInfo(SpuSaveVo vo);

    void saveBaseInfo(SpuInfoEntity infoEntity);

    PageUtils queryPageByCondition(Map<String, Object> params);

    /**
     * 商品上架
     * @param spuId: 
     * @return: void
     */
    void up(Long spuId) ;
}

