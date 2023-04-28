package com.akagiyui.edgeconnect.entity.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 注册请求体
 * @author AkagiYui
 */
@Data
public class RegisterConfirmRequest {
    /**
     * 邮箱
     */
    @Email(message = "Email format is incorrect")
    @NotBlank(message = "Email cannot be empty")
    private String email;
    /**
     * 验证码
     */
    @NotBlank(message = "Verify code cannot be empty")
    private String verifyCode;
}
