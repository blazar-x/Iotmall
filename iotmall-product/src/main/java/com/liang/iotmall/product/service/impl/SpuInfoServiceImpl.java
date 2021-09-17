package com.liang.iotmall.product.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.liang.common.constant.ProductConstant;
import com.liang.common.to.SkureductionTo;
import com.liang.common.to.SpuBoundTo;
import com.liang.common.to.es.SkuEsModel;
import com.liang.common.utils.R;
import com.liang.iotmall.product.entity.*;
import com.liang.iotmall.product.feign.CouponFeignService;
import com.liang.iotmall.product.feign.SearchFeignService;
import com.liang.iotmall.product.feign.WareFeignService;
import com.liang.iotmall.product.service.*;
import com.liang.iotmall.product.vo.*;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.common.utils.PageUtils;
import com.liang.common.utils.Query;

import com.liang.iotmall.product.dao.SpuInfoDao;
import org.springframework.transaction.annotation.Transactional;


/**
 * 高级篇再来完善
 */
@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Autowired
    SpuInfoDescService descService;
    @Autowired
    SpuImagesService spuImagesService;
    @Autowired
    AttrService attrService;
    @Autowired
    ProductAttrValueService attrValueService;
    @Autowired
    SkuInfoService skuInfoService;
    @Autowired
    SkuImagesService skuImagesService;
    @Autowired
    SkuSaleAttrValueService skuSaleAttrValueService;
    @Autowired
    CouponFeignService couponFeignService;
    @Autowired
    BrandService brandService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    WareFeignService wareFeignService;
    @Autowired
    SearchFeignService searchFeignService;


    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveSpuInfo(SpuSaveVo vo) {
        //1.保存spu基本信息
        SpuInfoEntity infoEntity = new SpuInfoEntity();
        BeanUtils.copyProperties(vo, infoEntity);
        infoEntity.setCreateTime(new Date());
        infoEntity.setUpdateTime(new Date());
        this.saveBaseInfo(infoEntity);

        //2.保存Spu的描述图片
        List<String> decript = vo.getDecript();
        SpuInfoDescEntity descEntity = new SpuInfoDescEntity();
        descEntity.setSpuId(infoEntity.getId());
        descEntity.setDecript(String.join(",", decript));
        descService.saveSpuInfoDesc(descEntity);
        //3.保存spu的图片集
        List<String> images = vo.getImages();
        spuImagesService.saveImages(infoEntity.getId(), images);
        //4.保存spu的规格参数
        List<BaseAttrs> baseAttrs = vo.getBaseAttrs();
        List<ProductAttrValueEntity> valueEntities = baseAttrs.stream().map(attr -> {
            ProductAttrValueEntity attrValueEntity = new ProductAttrValueEntity();
            attrValueEntity.setAttrId(attr.getAttrId());
            attrValueEntity.setAttrName(attrService.getById(attr.getAttrId()).getAttrName());
            attrValueEntity.setAttrValue(attr.getAttrValues());
            attrValueEntity.setQuickShow(attr.getShowDesc());
            attrValueEntity.setSpuId(infoEntity.getId());

            return attrValueEntity;
        }).collect(Collectors.toList());
        attrValueService.saveProductAttr(valueEntities);
        //保存spu的积分信息 iotmall_sms->sms_spu_bounds
        Bounds bounds = vo.getBounds();
        SpuBoundTo boundTo = new SpuBoundTo();
        BeanUtils.copyProperties(bounds, boundTo);
        boundTo.setSpuId(infoEntity.getId());
        R r = couponFeignService.saveSpuBounds(boundTo);
        if (r.getCode() != 0) {
            log.error("远程保存spu积分信息失败");
        }

        //5.保存spu当前对应的所有sku信息
        //5.1sku的基本信息
        List<Skus> skus = vo.getSkus();
        if (skus != null && skus.size() > 0) {
            skus.forEach(item -> {
                String defaultImg = "";
                for (Images image : item.getImages()) {
                    if (image.getDefaultImg() == 1) {
                        defaultImg = image.getImgUrl();
                    }
                }
                SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
                BeanUtils.copyProperties(item, skuInfoEntity);
                skuInfoEntity.setBrandId(infoEntity.getBrandId());
                skuInfoEntity.setCatalogId(infoEntity.getCatalogId());
                skuInfoEntity.setSaleCount(0L);
                skuInfoEntity.setSpuId(infoEntity.getId());
                skuInfoEntity.setSkuDefaultImg(defaultImg);
                skuInfoService.saveSkuInfo(skuInfoEntity);
                //5.2 sku的图片信息
                Long skuId = skuInfoEntity.getSkuId();
                List<SkuImagesEntity> imagesEntities = item.getImages().stream().map(img -> {
                    SkuImagesEntity imagesEntity = new SkuImagesEntity();
                    imagesEntity.setSkuId(skuId);
                    imagesEntity.setImgUrl(img.getImgUrl());
                    imagesEntity.setDefaultImg(img.getDefaultImg());
                    return imagesEntity;
                }).filter(entity -> {
                    //true需要,flase剔除
                    return !StringUtils.isNullOrEmpty(entity.getImgUrl());
                }).collect(Collectors.toList());
                skuImagesService.saveBatch(imagesEntities); //TODO 没有图片路径的无须保存
                //5.3 sku的销售属性
                List<Attr> attr = item.getAttr();
                List<SkuSaleAttrValueEntity> skuSaleAttrValueEntities = attr.stream().map(a -> {
                    SkuSaleAttrValueEntity skuSaleAttrValueEntity = new SkuSaleAttrValueEntity();
                    BeanUtils.copyProperties(a, skuSaleAttrValueEntity);
                    skuSaleAttrValueEntity.setSkuId(skuId);
                    return skuSaleAttrValueEntity;
                }).collect(Collectors.toList());
                skuSaleAttrValueService.saveBatch(skuSaleAttrValueEntities);

                //5.4 sku的优惠,满减信息
                SkureductionTo skureductionTo = new SkureductionTo();
                BeanUtils.copyProperties(item, skureductionTo);
                skureductionTo.setSkuId(skuId);
                if (skureductionTo.getFullCount() > 0 || skureductionTo.getFullPrice().compareTo(new BigDecimal(0)) == 1) {
                    R r1 = couponFeignService.saveSkuReduction(skureductionTo);
                    if (r1.getCode() != 0) {
                        log.error("远程保存spu优惠信息失败");
                    }
                }
            });
        }
    }

    @Override
    public void saveBaseInfo(SpuInfoEntity infoEntity) {
        this.baseMapper.insert(infoEntity);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<SpuInfoEntity> wrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (!StringUtils.isNullOrEmpty(key)) {
            wrapper.and(w -> {
                w.eq("id", key).or().like("spu_name", key);
            });
        }
        String status = (String) params.get("status");
        if (!StringUtils.isNullOrEmpty(status)) {
            wrapper.and(w -> {
                w.eq("publish_status", status);
            });
        }
        String brandId = (String) params.get("brandId");
        if (!StringUtils.isNullOrEmpty(brandId) && !"0".equalsIgnoreCase(brandId)) {
            wrapper.and(w -> {
                w.eq("brand_id", brandId);
            });
        }
        String catelogId = (String) params.get("catelogId");
        if (!StringUtils.isNullOrEmpty(catelogId) && !"0".equalsIgnoreCase(catelogId)) {
            wrapper.and(w -> {
                w.eq("catalog_id", catelogId);
            });
        }

        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public void up(Long spuId) {
        //1.查出当前spuid对应的所有sku信息，品牌的名字
        List<SkuInfoEntity> skus= skuInfoService.getSkusBySpuId(spuId);
        List<Long> skuIdList = skus.stream().map(SkuInfoEntity::getSkuId).collect(Collectors.toList());

        //TODO 查询当前sku的所有可以用来被检索的规格属性
        List<ProductAttrValueEntity> baseAttrs = attrValueService.baseAttrlistforspu(spuId);
        List<Long> attrIds=baseAttrs.stream().map(attr->{
            return attr.getAttrId();
        }).collect(Collectors.toList());

        List<Long> searchAttrIds = attrService.selectSearchAttrs(attrIds);
        Set<Long> idSet= new HashSet<>(searchAttrIds);


        List<SkuEsModel.Attrs> attrsList = baseAttrs.stream().filter(item -> {
            return idSet.contains(item.getAttrId());
        }).map(item -> {
            SkuEsModel.Attrs attrs1 = new SkuEsModel.Attrs();
            BeanUtils.copyProperties(item, attrs1);
            return attrs1;
        }).collect(Collectors.toList());

        //TODO 发送远程调用，库存系统查询是否有库存
        Map<Long, Boolean> stockMap=null;
        try{
            R hasStock = wareFeignService.getSkusHasStock(skuIdList);
            TypeReference<List<SkuHasStockVo>> typeReference = new TypeReference<List<SkuHasStockVo>>() {
            };
            stockMap = hasStock.getData(typeReference).stream().collect(Collectors.toMap(SkuHasStockVo::getSkuId, SkuHasStockVo::getHasStcok));
        }catch (Exception e){
            log.error("库存查询服务异常：原因{}",e);
        }

        //2.封装每个sku的信息
        Map<Long, Boolean> finalStockMap = stockMap;
        List<SkuEsModel> upProducts = skus.stream().map(sku -> {
            //组装需要的数据
            SkuEsModel esModel = new SkuEsModel();
            BeanUtils.copyProperties(sku,esModel);
            //skuPrice,skuImg
            esModel.setSkuPrice(sku.getPrice());
            esModel.setSkuImg(sku.getSkuDefaultImg());
            //,hasStock,hotScore,
            //设置库存信息
            if(finalStockMap ==null){
                esModel.setHasStock(true);
            }else{
                esModel.setHasStock(finalStockMap.get(sku.getSkuId()));
            }

            //TODO 热度评分
            esModel.setHotScore(0L);
            //TODO 查询品牌信息，分类信息
            BrandEntity brandEntity = brandService.getById(esModel.getBrandId());
            esModel.setBrandName(brandEntity.getName());
            esModel.setBrandImg(brandEntity.getLogo());
            CategoryEntity categoryEntity = categoryService.getById(esModel.getCatalogId());
            esModel.setCatelogName(categoryEntity.getName());
            //设置检索属性
            esModel.setAttrs(attrsList);
            return esModel;
        }).collect(Collectors.toList());

        //// TODO: 2021/5/24 将数据发送给es进行保存
        R r = searchFeignService.productStatusUp(upProducts);
        if(r.getCode()==0){
            //远程调用成功
            //// TODO: 2021/5/24 修改当前spu状态
            baseMapper.updateSpuStatus(spuId, ProductConstant.StatusEnum.SPU_UP.getCode());
        }else {
            //远程调用失败
            //// TODO: 2021/5/24 重复调用？接口幂等性；重试机制？
            //feign调用流程
            //1.构造请求数据，将对象转为json
            //2.发送请求进行执行（执行成功会解码响应数据）
            //3.执行请求会有重试机制


        }
    }


}