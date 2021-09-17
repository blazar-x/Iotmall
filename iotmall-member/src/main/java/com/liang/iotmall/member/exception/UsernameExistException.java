package com.liang.iotmall.member.exception;

/**
 * @author: Mr.Liang
 * @date: 2021/5/28 15:13
 * @Version: 1.0
 */
public class UsernameExistException extends  RuntimeException{
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public UsernameExistException() {
        super("用户名已存在");
    }
}
