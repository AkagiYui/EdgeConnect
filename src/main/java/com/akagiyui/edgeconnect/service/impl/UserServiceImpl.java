package com.akagiyui.edgeconnect.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.akagiyui.edgeconnect.component.RedisCache;
import com.akagiyui.edgeconnect.entity.LoginUserDetails;
import com.akagiyui.edgeconnect.entity.User;
import com.akagiyui.edgeconnect.entity.request.EmailVerifyCodeRequireRequest;
import com.akagiyui.edgeconnect.entity.request.RegisterConfirmRequest;
import com.akagiyui.edgeconnect.exception.CustomException;
import com.akagiyui.edgeconnect.mapper.UserMapper;
import com.akagiyui.edgeconnect.service.MailService;
import com.akagiyui.edgeconnect.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.akagiyui.edgeconnect.component.ResponseEnum.*;

/**
 * 用户表 服务实现类
 * @author AkagiYui
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    UserMapper userMapper;

    @Resource
    RedisCache redisCache;

    @Value("${edge.email.verify.timeout}")
    private long emailVerifyTimeout;

    @Resource
    MailService mailService;

    /**
     * 获取当前用户
     * @return 用户
     */
    @Override
    public User getUser() {
        // 从 SecurityContextHolder 中获取用户信息
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUserDetails userDetails = (LoginUserDetails) authentication.getPrincipal();
        return userDetails.getUser();
    }

    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 用户
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userMapper.getUser(username);
        Preconditions.checkNotNull(user, "User not found");
        // 查询用户权限
        List<String> permissions = new ArrayList<>();
        if (user.getIsAdmin()) {
            permissions.add("ROLE_ADMIN");
        }
        return new LoginUserDetails(user, permissions);
    }

    /**
     * 注册
     * @param registerConfirmRequest 注册请求体
     * @return 是否成功
     */
    @Override
    public boolean confirmRegister(RegisterConfirmRequest registerConfirmRequest) {
        // 从 redis 取回验证码
        String redisKey = "emailVerifyCode:" + registerConfirmRequest.getEmail();
        String verifyCode = redisCache.get(redisKey);
        if (verifyCode == null) {
            throw new CustomException(VERIFY_CODE_NOT_FOUND);
        }
        // 检查验证码是否正确
        if (!Objects.equals(registerConfirmRequest.getVerifyCode(), verifyCode)) {
            throw new CustomException(VERIFY_CODE_NOT_FOUND);
        }
        // 从 redis 取回用户注册信息
        EmailVerifyCodeRequireRequest verifyRequest = redisCache.get("registerInfo:" + registerConfirmRequest.getEmail());
        if (verifyRequest == null) {
            log.error("Register info not found: {}", registerConfirmRequest.getEmail());
            throw new CustomException(VERIFY_CODE_NOT_FOUND);
        }
        // 转换为用户对象
        User user = new User();
        user.setUsername(verifyRequest.getUsername());
        user.setPassword((new BCryptPasswordEncoder()).encode(verifyRequest.getUsername() + user.getPassword()));
        user.setEmail(verifyRequest.getEmail());
        try {
            return save(user);
        } finally {
            // 删除 redis 中的验证码和注册信息
            redisCache.delete(redisKey);
            redisCache.delete("registerInfo:" + registerConfirmRequest.getEmail());
        }
    }

    @Override
    public boolean sendEmailVerifyCode(EmailVerifyCodeRequireRequest verifyRequest) {
        // 检查该邮箱是否在 redis 中等待验证
        String redisKey = "emailVerifyCode:" + verifyRequest.getEmail();
        if (redisCache.hasKey(redisKey)) {
            throw new CustomException(EMAIL_EXIST);
        }
        // 检查该邮箱是否已经注册
        if (userMapper.isEmailExist(verifyRequest.getEmail())) {
            throw new CustomException(EMAIL_EXIST);
        }
        // 检查用户名是否已经注册
        if (userMapper.isUserExist(verifyRequest.getUsername())) {
            throw new CustomException(USER_EXIST);
        }
        // 生成验证码
        String verifyCode = RandomUtil.randomNumbers(6);
        redisCache.set(redisKey, verifyCode);
        redisCache.expire(redisKey, emailVerifyTimeout, TimeUnit.MINUTES);
        mailService.sendEmailVerifyCode(verifyRequest.getEmail(), verifyCode, emailVerifyTimeout);
        // 将注册信息存入 redis
        String registerInfoKey = "registerInfo:" + verifyRequest.getEmail();
        redisCache.set(registerInfoKey, verifyRequest);
        redisCache.expire(registerInfoKey, emailVerifyTimeout + 1, TimeUnit.MINUTES);
        return true;
    }

    @Override
    public boolean isUserExist(String username) {
        return userMapper.isUserExist(username);
    }

    @Override
    public boolean isUserExist(Long userId) {
        return userMapper.isUserExist(userId);
    }
}
