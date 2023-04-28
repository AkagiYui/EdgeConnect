package com.akagiyui.edgeconnect.service;

import com.akagiyui.edgeconnect.entity.User;
import com.akagiyui.edgeconnect.entity.request.EmailVerifyCodeRequireRequest;
import com.akagiyui.edgeconnect.entity.request.RegisterConfirmRequest;
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
     * 获取用户
     * @param username 用户名
     * @return 用户
     */
    User getUser(String username);

    /**
     * 获取用户
     * @param userId 用户 ID
     * @return 用户
     */
    User getUser(Long userId);

    /**
     * 获取当前用户
     * @return 用户
     */
    User getUser();

    /**
     * 用户是否存在
     * @param username 用户名
     * @return 是否存在
     */
    Boolean isUserExist(String username);

    /**
     * 用户是否存在
     * @param userId 用户 ID
     * @return 是否存在
     */
    Boolean isUserExist(Long userId);

    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 用户
     */
    @Override
    UserDetails loadUserByUsername(String username);

    /**
     * 注册
     * @param registerConfirmRequest 注册请求体
     * @return 是否成功
     */
    boolean confirmRegister(RegisterConfirmRequest registerConfirmRequest);

    /**
     * 申请邮箱验证码
     * @param verifyRequest 验证请求体
     * @return 是否成功
     */
    boolean preRegister(EmailVerifyCodeRequireRequest verifyRequest);
}
