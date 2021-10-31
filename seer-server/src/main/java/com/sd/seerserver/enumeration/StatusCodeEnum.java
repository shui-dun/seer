package com.sd.seerserver.enumeration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum StatusCodeEnum {
    SUCCESS(0, "成功"),
    USER_NOT_EXIST(1, "用户不存在"),
    INCORRECT_CREDENTIALS(2, "密码错误"),
    SERVER_ERROR(3, "服务端错误"),
    UNAUTHENTICATED(4, "未登录"),
    USER_ALREADY_EXIST(5, "用户名已经存在"),
    UNAUTHORIZED(6, "未授权的操作"),
    COMMENT_NOT_EXIST(7, "评论不存在"),
    COMMENT_TOO_LONG(8, "评论文本过长"),
    PET_NOT_EXIST(9, "精灵不存在");


    private final int code;
    private final String msg;

}
