package com.liang.iotmall.member.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * @author: Mr.Liang
 * @date: 2021/5/28 14:58
 * @Version: 1.0
 */
@Data
public class MemberRegistVo {

    private String userName;

    private String password;

    private String phone;
}
