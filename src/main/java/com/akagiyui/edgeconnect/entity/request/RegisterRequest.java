package com.akagiyui.edgeconnect.entity.request;

import lombok.Data;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.Email;

/**
 * 注册请求体
 * @author AkagiYui
 */
@Data
public class RegisterRequest {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    @Email
    private String email;
    /**
     * 昵称
     */
    @Nullable
    private String nickname;
}
