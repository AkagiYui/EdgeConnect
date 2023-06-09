package com.akagiyui.edgeconnect.entity.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 邮箱验证码发送请求 请求体
 * @author AkagiYui
 */
@Data
public class EmailVerifyCodeRequireRequest {
    /**
     * 邮箱
     */
    @NotBlank(message = "Email cannot be empty")
    @NotNull(message = "Email cannot be empty")
    @Email(message = "Email format is incorrect")
    private String email;
    /**
     * 用户名
     */
    @NotBlank(message = "Username cannot be empty")
    @NotNull(message = "Username cannot be empty")
    @Size(min = 5, max = 20, message = "Username length must be between 5 and 20")
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "Password cannot be empty")
    @NotNull(message = "Password cannot be empty")
    @Size(min = 8, max = 64, message = "Password length must be less than 8")
    private String password;
    /**
     * 昵称
     */
    @Size(max = 20, message = "Nickname length must be less than 20")
    private String nickname;
}
