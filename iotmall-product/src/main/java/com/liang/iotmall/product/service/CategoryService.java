package com.liang.iotmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.common.utils.PageUtils;
import com.liang.iotmall.product.entity.CategoryEntity;
import com.liang.iotmall.product.vo.Catelog2Vo;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author liangpengfei
 * @email pengf.liang@foxmail.com
 * @date 2021-03-11 12:38:17
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listWithTree();

    /***
     * 找到catelogId的完整路径
     * [父/子/孙]
     * @param catelogId:
     * @return: java.lang.Long[]
     */
    Long[] findCatelogPath(Long catelogId);

    void updateCascade(CategoryEntity category);

    List<CategoryEntity> getLevel1Categorys();

    Map<String, List<Catelog2Vo>>getCatelogJson() ;
}

