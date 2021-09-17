package com.liang.iotmall.product;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liang.iotmall.product.dao.AttrGroupDao;
import com.liang.iotmall.product.dao.SkuSaleAttrValueDao;
import com.liang.iotmall.product.entity.BrandEntity;
import com.liang.iotmall.product.service.BrandService;
import com.liang.iotmall.product.service.CategoryService;
import com.liang.iotmall.product.vo.SpuItemAttrGroupVo;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class IotmallProductApplicationTests {
    @Autowired
    AttrGroupDao attrGroupDao;


    @Autowired
    BrandService brandService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    SkuSaleAttrValueDao saleAttrValueDao;

    @Autowired
    RedissonClient redissonClient;

    @Test
    public void testRedis() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        //保存
        ops.set("hello","world_"+ UUID.randomUUID().toString());

        //查询
        String hello = ops.get("hello");
        System.out.println("之前保存的数据是："+hello);

    }


    @Test
    public void redisson() {
        System.out.println(saleAttrValueDao.getSaleAttrBySpuId((long) 4));
//        List<SpuItemAttrGroupVo> spuId = attrGroupDao.getAttrGroupWithAttrsBySpuId(4L, 225L);
//        System.out.println(spuId);

//        System.out.println(redissonClient);
    }

    @Test
    public void testFindPath() {
        Long[] catelogPath = categoryService.findCatelogPath(225L);
        log.info("完整路径：{}", Arrays.asList(catelogPath));

    }


    @Test
    void contextLoads() {
//		BrandEntity brandEntity = new BrandEntity();
//		brandEntity.setBrandId(1L);
//		brandEntity.setDescript("为发烧而生");
//		brandService.updateById(brandEntity);


//		brandEntity.setName("小米");
//		brandService.save(brandEntity);
//		System.out.println("保存成功");

        List<BrandEntity> list = brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", "1"));
        list.forEach(System.out::println);

    }

}
