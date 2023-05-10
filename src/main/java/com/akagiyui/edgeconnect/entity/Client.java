package com.akagiyui.edgeconnect.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * 应用实体
 * @author AkagiYui
 */
@Data
public class Client extends BaseEntity {
    /**
     * 应用名称
     */
    private String name;
    /**
     * 所有者
     */
    private Long owner;
    /**
     * 应用id
     */
    private String clientId;
    /**
     * 应用密钥
     */
    private String clientSecret;
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
}
