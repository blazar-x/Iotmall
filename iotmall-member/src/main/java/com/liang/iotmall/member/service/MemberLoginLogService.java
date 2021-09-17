package com.liang.iotmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.common.utils.PageUtils;
import com.liang.iotmall.member.entity.MemberLoginLogEntity;

import java.util.Map;

/**
 * 会员登录记录
 *
 * @author liangpengfei
 * @email pengf.liang@foxmail.com
 * @date 2021-03-15 10:03:18
 */
public interface MemberLoginLogService extends IService<MemberLoginLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

