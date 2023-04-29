package com.akagiyui.edgeconnect.service;

import com.akagiyui.edgeconnect.entity.User;
import com.akagiyui.edgeconnect.entity.request.EmailVerifyCodeRequireRequest;
import com.akagiyui.edgeconnect.entity.request.RegisterConfirmRequest;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 用户 Service 接口
 * @author AkagiYui
 */
public interface UserService extends IService<User>, UserDetailsService {
    /**
     * 从 Security 获取当前用户
     * @return 用户
     */
    User getUser();

    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 用户
     */
    @Override
    UserDetails loadUserByUsername(String username);

    /**
     * 确认注册
     * @param registerConfirmRequest 注册请求体
     * @return 是否成功
     */
    boolean confirmRegister(RegisterConfirmRequest registerConfirmRequest);

    /**
     * 申请邮箱验证码
     * @param verifyRequest 验证请求体
     * @return 是否成功
     */
    boolean sendEmailVerifyCode(EmailVerifyCodeRequireRequest verifyRequest);

    /**
     * 用户是否存在
     * @param username 用户名
     * @return 是否存在
     */
    boolean isUserExist(String username);

    /**
     * 用户是否存在
     * @param userId 用户 ID
     * @return 是否存在
     */
    boolean isUserExist(Long userId);
}
