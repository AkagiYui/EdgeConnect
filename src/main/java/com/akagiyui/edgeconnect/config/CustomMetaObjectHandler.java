package com.akagiyui.edgeconnect.config;

import com.akagiyui.edgeconnect.entity.Application;
import com.akagiyui.edgeconnect.entity.User;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自定义字段填充策略
 * @author AkagiYui
 */
@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入时填充
     * @param metaObject 元数据对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 创建时间
        Date date = new Date();
        this.setFieldValByName("createTime", date, metaObject);
        this.setFieldValByName("updateTime", date, metaObject);

        Object originalObject = metaObject.getOriginalObject();
        if (originalObject instanceof User user) { // 用户表
            if (user.getIsDeleted() == null) {
                // 删除标记
                this.setFieldValByName("isDeleted", false, metaObject);
            }
            if (user.getIsDisabled() == null) {
                // 禁用标记
                this.setFieldValByName("isDisabled", false, metaObject);
            }
            // 如果昵称为空，则使用用户名
            if (user.getNickname() == null) {
                this.setFieldValByName("nickname", user.getUsername(), metaObject);
            }
        } else if (originalObject instanceof Application app) { // 应用表
            if (app.getIsDeleted() == null) {
                // 删除标记
                this.setFieldValByName("isDeleted", false, metaObject);
            }
            if (app.getIsDisabled() == null) {
                // 禁用标记
                this.setFieldValByName("isDisabled", false, metaObject);
            }
        }
    }

    /**
     * 更新时填充
     * @param metaObject 元数据对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时间
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
