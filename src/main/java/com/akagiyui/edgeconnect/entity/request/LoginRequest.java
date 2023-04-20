package com.akagiyui.edgeconnect.entity.request;

import lombok.Data;

/**
 * 登录请求体
 * @author AkagiYui
 */
@Data
public class LoginRequest {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
