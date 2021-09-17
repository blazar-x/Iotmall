package com.liang.iotmall.member.service.impl;

import com.liang.iotmall.member.dao.MemberLevelDao;
import com.liang.iotmall.member.entity.MemberLevelEntity;
import com.liang.iotmall.member.exception.PhoneExistException;
import com.liang.iotmall.member.exception.UsernameExistException;
import com.liang.iotmall.member.vo.MemberLoginVo;
import com.liang.iotmall.member.vo.MemberRegistVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.common.utils.PageUtils;
import com.liang.common.utils.Query;

import com.liang.iotmall.member.dao.MemberDao;
import com.liang.iotmall.member.entity.MemberEntity;
import com.liang.iotmall.member.service.MemberService;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Autowired
    MemberLevelDao memberLevelDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberEntity> page = this.page(
                new Query<MemberEntity>().getPage(params),
                new QueryWrapper<MemberEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void regist(MemberRegistVo vo) {
        MemberEntity memberEntity = new MemberEntity();
        //设置默认等级
       MemberLevelEntity levelEntity = memberLevelDao.getDefaultLevel();
        memberEntity.setLevelId(levelEntity.getId());

        //检查用户名和手机号是否唯一
        checkPhoneUnique(vo.getPhone());
        checkUsernameUnique(vo.getUserName());


        memberEntity.setMobile(vo.getPhone());
        memberEntity.setUsername(vo.getUserName());
        memberEntity.setNickname(vo.getUserName());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(vo.getPassword());
        memberEntity.setPassword(encode);

        //其他的默认信息

        //保存
        this.baseMapper.insert(memberEntity);

    }

    @Override
    public void checkUsernameUnique(String username) throws UsernameExistException{
        Integer username1 = this.baseMapper.selectCount(new QueryWrapper<MemberEntity>().eq("username", username));
        if(username1>0){
            throw new UsernameExistException();
        }

    }

    @Override
    public void checkPhoneUnique(String phone) throws  PhoneExistException {
        Integer mobile = this.baseMapper.selectCount(new QueryWrapper<MemberEntity>().eq("mobile", phone));
        if(mobile>0){
            throw new PhoneExistException();
        }
    }

    @Override
    public MemberEntity login(MemberLoginVo vo) {
        String loginacct = vo.getLoginacct();
        String password = vo.getPassword();

        MemberEntity entity = this.baseMapper.selectOne(new QueryWrapper<MemberEntity>().eq("username", loginacct).or().eq("mobile", loginacct));
        if(entity==null){
            return null;
        }else {
            String passwordDb = entity.getPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            boolean matches = passwordEncoder.matches(password, passwordDb);
            if(matches){
                return entity;
            }else {
                return null;
            }
        }
    }


}