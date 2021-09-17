package com.liang.iotmall.coupon.service.impl;

import com.liang.common.to.MemberPrice;
import com.liang.common.to.SkureductionTo;
import com.liang.iotmall.coupon.entity.MemberPriceEntity;
import com.liang.iotmall.coupon.entity.SkuLadderEntity;
import com.liang.iotmall.coupon.service.MemberPriceService;
import com.liang.iotmall.coupon.service.SkuLadderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.common.utils.PageUtils;
import com.liang.common.utils.Query;

import com.liang.iotmall.coupon.dao.SkuFullReductionDao;
import com.liang.iotmall.coupon.entity.SkuFullReductionEntity;
import com.liang.iotmall.coupon.service.SkuFullReductionService;


@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionDao, SkuFullReductionEntity> implements SkuFullReductionService {

    @Autowired
    SkuLadderService skuLadderService;
    @Autowired
    MemberPriceService memberPriceService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuFullReductionEntity> page = this.page(
                new Query<SkuFullReductionEntity>().getPage(params),
                new QueryWrapper<SkuFullReductionEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSkuReduction(SkureductionTo skureductionTo) {
        //1.保存满减打折
        SkuLadderEntity skuLadderEntity = new SkuLadderEntity();
        skuLadderEntity.setSkuId(skureductionTo.getSkuId());
        skuLadderEntity.setFullCount(skureductionTo.getFullCount());
        skuLadderEntity.setDiscount(skureductionTo.getDiscount());
        skuLadderEntity.setAddOther(skureductionTo.getCountStatus());
        if (skureductionTo.getFullCount() > 0) {
            skuLadderService.save(skuLadderEntity);
        }
        //2.sku的满减信息
        SkuFullReductionEntity reductionEntity = new SkuFullReductionEntity();
        BeanUtils.copyProperties(skureductionTo, reductionEntity);
        if (reductionEntity.getFullPrice().compareTo(new BigDecimal(0)) == 1) {
            this.save(reductionEntity);
        }
        //3.会员价格
        List<MemberPrice> memberPrice = skureductionTo.getMemberPrice();
        List<MemberPriceEntity> collect = memberPrice.stream().map(item -> {
            MemberPriceEntity priceEntity = new MemberPriceEntity();
            priceEntity.setSkuId(skureductionTo.getSkuId());
            priceEntity.setMemberLevelId(item.getId());
            priceEntity.setMemberLevelName(item.getName());
            priceEntity.setMemberPrice(item.getPrice());
            priceEntity.setAddOther(1);
            return priceEntity;
        }).filter(item -> {
            return item.getMemberPrice().compareTo(new BigDecimal(0)) == 1;
        }).collect(Collectors.toList());
        memberPriceService.saveBatch(collect);
    }
}