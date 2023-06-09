package com.akagiyui.edgeconnect.entity.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @NotNull(message = "Username cannot be empty")
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20")
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "Password cannot be empty")
    @NotNull(message = "Password cannot be empty")
    private String password;
}
