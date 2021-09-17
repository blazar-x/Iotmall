package com.liang.iotmall.member.exception;

/**
 * @author: Mr.Liang
 * @date: 2021/5/28 15:13
 * @Version: 1.0
 */
public class PhoneExistException extends RuntimeException{


    public PhoneExistException() {
        super("手机号存在");
    }
}
