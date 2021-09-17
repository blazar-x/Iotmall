package com.liang.iotmall.member.dao;

import com.liang.iotmall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 *
 * @author liangpengfei
 * @email pengf.liang@foxmail.com
 * @date 2021-03-15 10:03:18
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {

}
