package com.akagiyui.edgeconnect.service.impl;

import com.akagiyui.edgeconnect.entity.LoginUserDetails;
import com.akagiyui.edgeconnect.entity.User;
import com.akagiyui.edgeconnect.mapper.UserMapper;
import com.akagiyui.edgeconnect.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Preconditions;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户表 服务实现类
 * @author AkagiYui
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    UserMapper userMapper;

    /**
     * 获取所有用户
     * @return 用户列表
     */
    @Override
    public List<User> getAllUser() {
        return userMapper.selectList(null);
    }

    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 用户
     */
    @Override
    public User getUser(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        return userMapper.selectOne(wrapper);
    }

    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 用户
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = getUser(username);
        Preconditions.checkNotNull(user, "用户不存在");
        return new LoginUserDetails(user);
    }
}
