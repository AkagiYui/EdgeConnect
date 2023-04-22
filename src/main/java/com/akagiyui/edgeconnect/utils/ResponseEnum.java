package com.akagiyui.edgeconnect.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应状态枚举
 * @author AkagiYui
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {
    /**
     * 成功
     */
    SUCCESS(10000, ""),
    /**
     * 错误
     */
    GENERAL_ERROR(10001, "General error"),
    /**
     * 内部错误
     */
    INTERNAL_ERROR(10002, "Internal error"),
    /**
     * 未找到
     */
    NOT_FOUND(10003, "Not found"),
    /**
     * 未授权
     */
    UNAUTHORIZED(10004, "Unauthorized"),
    /**
     * 用户已存在
     */
    USER_EXIST(10005, "User exist"),
    /**
     * 参数错误
     */
    BAD_REQUEST(10006, "Bad request");

    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 消息
     */
    private final String msg;
}
