package com.akagiyui.edgeconnect.entity.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录请求体
 * @author AkagiYui
 */
@Data
public class LoginRequest {
    /**
     * 用户名
     */
    @NotBlank(message = "Username cannot be empty")
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "Password cannot be empty")
    private String password;
}
