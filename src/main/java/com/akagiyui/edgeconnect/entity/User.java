package com.akagiyui.edgeconnect.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 用户实体
 * @author AkagiYui
 */
@Data
public class User {
    /**
     * 用户ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 是否禁用
     */
    private Boolean isDisabled;
    /**
     * 创建时间
     */
    private Timestamp createTime;
}
