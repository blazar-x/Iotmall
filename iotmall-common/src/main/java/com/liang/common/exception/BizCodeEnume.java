package com.liang.common.exception;

/**
 * 错误码枚举
 *
 * @author: Mr.Liang
 * @date: 2021/4/9 14:28
 * @Version: 1.0
 */

public enum BizCodeEnume {
    UNKNOW_EXCEPTION(10000, "系统未知异常"),
    VAILD_EXCEPTION(10001, "参数校验失败"),
    SMS_CODE_EXCEPTION(10002, "短信验证码频率过高"),
    PRODUCT_UP_EXCEPTION(11000,"商品上架异常"),
    USER_EXIST_EXCEPTION(15001,"用户名存在异常"),
    LOGIN_PASSWORD_EXCEPTION(15003,"账号密码错误"),
    PHONE_EXIST_EXCEPTION(15002,"手机号存在异常");

    private Integer code;
    private String msg;

    BizCodeEnume(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
