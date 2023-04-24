package com.akagiyui.edgeconnect.entity.response;

import lombok.Data;

/**
 * 用户信息响应
 * @author AkagiYui
 */
@Data
public class UserInfoResponse {
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 邮箱
     */
    private String email;
}
