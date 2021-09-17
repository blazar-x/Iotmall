package com.liang.iotmall.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.liang.common.utils.Query;
import com.liang.iotmall.product.entity.AttrEntity;
import com.liang.iotmall.product.service.AttrService;
import com.liang.iotmall.product.vo.AttrGroupWithAttrsVo;
import com.liang.iotmall.product.vo.SpuItemAttrGroupVo;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.common.utils.PageUtils;

import com.liang.iotmall.product.dao.AttrGroupDao;
import com.liang.iotmall.product.entity.AttrGroupEntity;
import com.liang.iotmall.product.service.AttrGroupService;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    AttrService attrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        String key = (String) params.get("key");
        //select * from pms_attr_group where catelog_id=? and(attr_group_id=key or attr_group_name like %key%)
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<AttrGroupEntity>();
        if (!StringUtils.isEmptyOrWhitespaceOnly(key)) {
            wrapper.and((obj) -> {
                obj.eq("attr_group_id", key).or().like("attr_group_name", key);
            });
        }
        if (catelogId == 0) {
            IPage<AttrGroupEntity> page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),
                    wrapper);
            return new PageUtils(page);
        } else {
            wrapper.eq("catelog_id", catelogId);
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params), wrapper);
            return new PageUtils(page);
        }
    }

    /**
     * 根据分类id找到所有属性分组和分组下的所有属性
     *
     * @param catelogId:
     * @return: java.util.List<com.liang.iotmall.product.vo.AttrGroupWithAttrsVo>
     */
    @Override
    public List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId) {
        //1.查询分组信息
        List<AttrGroupEntity> groupEntities = this.list(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId));
        //2.查询组里面的所有属性
        List<AttrGroupWithAttrsVo> vos = groupEntities.stream().map(group -> {
            AttrGroupWithAttrsVo vo = new AttrGroupWithAttrsVo();
            BeanUtils.copyProperties(group, vo);
            List<AttrEntity> attrs = attrService.getRelationAttr(vo.getAttrGroupId());
            vo.setAttrs(attrs);
            return vo;
        }).collect(Collectors.toList());
        return vos;
    }

    @Override
    public List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(Long spuId, Long catalogId) {
        //查出当前spu对应的所有属性的分组信息，以及当前分组下的所有属性对应的值
        List<SpuItemAttrGroupVo> vos= this.baseMapper.getAttrGroupWithAttrsBySpuId( spuId,  catalogId);
        return null;

    }

}