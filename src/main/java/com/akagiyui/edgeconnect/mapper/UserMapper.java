package com.akagiyui.edgeconnect.mapper;

import com.akagiyui.edgeconnect.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 用户表 Mapper 接口
 * @author AkagiYui
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据邮箱判断用户是否存在
     * @param email 邮箱
     * @return 是否存在
     */
    default boolean isEmailExist(String email) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail, email);
        return selectCount(wrapper) > 0;
    }
}
