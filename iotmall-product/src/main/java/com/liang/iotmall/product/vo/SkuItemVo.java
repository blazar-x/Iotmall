package com.liang.iotmall.product.vo;

import com.liang.iotmall.product.entity.SkuImagesEntity;
import com.liang.iotmall.product.entity.SkuInfoEntity;
import com.liang.iotmall.product.entity.SpuInfoDescEntity;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author: Mr.Liang
 * @date: 2021/5/27 19:04
 * @Version: 1.0
 */
@ToString
@Data
public class SkuItemVo {
        //1、sku基本信息的获取  pms_sku_info
        private SkuInfoEntity info;

        private boolean hasStock = true;

        //2、sku的图片信息    pms_sku_images
        private List<SkuImagesEntity> images;

        //3、获取spu的销售属性组合
        private List<SkuItemSaleAttrVo> saleAttr;

        //4、获取spu的介绍
        private SpuInfoDescEntity desc;

        //5、获取spu的规格参数信息
        private List<SpuItemAttrGroupVo> groupAttrs;

        //6、秒杀商品的优惠信息
        private SeckillSkuVo seckillSkuVo;

        @Data
        public static class  SkuItemBaseAttrVo{
                private Long attrId;
                private String attrName;
                private List<String> attrValues;
        }


}

