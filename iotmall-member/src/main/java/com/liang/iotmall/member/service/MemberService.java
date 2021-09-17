package com.liang.iotmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.common.utils.PageUtils;
import com.liang.iotmall.member.entity.MemberEntity;
import com.liang.iotmall.member.exception.PhoneExistException;
import com.liang.iotmall.member.exception.UsernameExistException;
import com.liang.iotmall.member.vo.MemberLoginVo;
import com.liang.iotmall.member.vo.MemberRegistVo;

import java.util.Map;

/**
 * 会员
 *
 * @author liangpengfei
 * @email pengf.liang@foxmail.com
 * @date 2021-03-15 10:03:18
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void regist(MemberRegistVo vo );

    void checkUsernameUnique(String username) throws UsernameExistException;

    void checkPhoneUnique(String phone) throws PhoneExistException;

    MemberEntity login(MemberLoginVo vo);
}

