package com.akagiyui.edgeconnect.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

import static com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID;

/**
 * 用户实体
 * @author AkagiYui
 */
@Data
public class User {
    /**
     * 用户ID
     * 使用 雪花算法 生成
     */
    @TableId(type = ASSIGN_ID)
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
    @TableField(fill = FieldFill.INSERT)
    private Boolean isDisabled;
    /**
     * 是否被删除
     */
    @TableField(fill = FieldFill.INSERT)
    private Boolean isDeleted;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
