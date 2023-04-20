package com.akagiyui.edgeconnect.service;

import com.akagiyui.edgeconnect.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * 用户 Service 接口
 * @author AkagiYui
 */
public interface UserService extends IService<User>, UserDetailsService {
    /**
     * 获取所有用户
     * @return 用户列表
     */
    List<User> getAllUser();

    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 用户
     */
    User getUser(String username);

    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 用户
     */
    @Override
    UserDetails loadUserByUsername(String username);
}
