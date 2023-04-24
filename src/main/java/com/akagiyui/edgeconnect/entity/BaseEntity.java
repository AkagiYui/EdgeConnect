package com.akagiyui.edgeconnect.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

import static com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID;

/**
 * 基础实体类
 * @author AkagiYui
 */
@Data
public class BaseEntity {
    /**
     * 记录ID
     * 使用 雪花算法 生成
     */
    @JsonIgnore
    @TableId(type = ASSIGN_ID)
    private Long id;
    /**
     * 创建时间
     */
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
