package com.liang.iotmall.member.dao;

import com.liang.iotmall.member.entity.MemberLevelEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员等级
 *
 * @author liangpengfei
 * @email pengf.liang@foxmail.com
 * @date 2021-03-15 10:03:18
 */
@Mapper
public interface MemberLevelDao extends BaseMapper<MemberLevelEntity> {


    MemberLevelEntity getDefaultLevel();
}
