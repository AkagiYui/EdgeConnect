package com.akagiyui.edgeconnect.entity.request;

import lombok.Data;

/**
 * @author AkagiYui
 */
@Data
public class EmailVerifyCodeRequest {
    /**
     * 邮箱
     */
    private String email;
    /**
     * 用户名
     */
    private String username;
}
