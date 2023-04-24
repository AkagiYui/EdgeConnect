package com.akagiyui.edgeconnect.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * 用户实体
 * @author AkagiYui
 */
@Data
public class User extends BaseEntity {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    @JsonIgnore
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
    @TableField(fill = FieldFill.INSERT)
    @JsonIgnore
    private Boolean isDisabled;
    /**
     * 是否被删除
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonIgnore
    private Boolean isDeleted;
}
