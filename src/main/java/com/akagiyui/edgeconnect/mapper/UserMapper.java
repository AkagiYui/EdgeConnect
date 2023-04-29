package com.akagiyui.edgeconnect.mapper;

import com.akagiyui.edgeconnect.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

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

    /**
     * 获取所有用户
     * @return 用户列表
     */
    default List<User> getAllUser() {
        return selectList(null);
    }

    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 用户
     */
    default User getUser(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        return selectOne(wrapper);
    }

    /**
     * 根据用户 ID 获取用户
     * @param userId 用户 ID
     * @return 用户
     */
    default User getUser(Long userId) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId, userId);
        return selectOne(wrapper);
    }

    /**
     * 用户是否存在
     * @param username 用户名
     * @return 是否存在
     */
    default boolean isUserExist(String username) {
        return getUser(username) != null;
    }

    /**
     * 用户是否存在
     * @param userId 用户 ID
     * @return 是否存在
     */
    default boolean isUserExist(Long userId) {
        return getUser(userId) != null;
    }
}
