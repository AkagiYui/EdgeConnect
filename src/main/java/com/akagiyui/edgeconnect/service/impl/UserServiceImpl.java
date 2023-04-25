package com.akagiyui.edgeconnect.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.akagiyui.edgeconnect.component.RedisCache;
import com.akagiyui.edgeconnect.entity.LoginUserDetails;
import com.akagiyui.edgeconnect.entity.User;
import com.akagiyui.edgeconnect.entity.request.EmailVerifyCodeRequest;
import com.akagiyui.edgeconnect.entity.request.RegisterRequest;
import com.akagiyui.edgeconnect.exception.CustomException;
import com.akagiyui.edgeconnect.mapper.UserMapper;
import com.akagiyui.edgeconnect.service.MailService;
import com.akagiyui.edgeconnect.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.akagiyui.edgeconnect.component.ResponseEnum.EMAIL_EXIST;
import static com.akagiyui.edgeconnect.component.ResponseEnum.USER_EXIST;

/**
 * 用户表 服务实现类
 * @author AkagiYui
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    UserMapper userMapper;

    @Resource
    RedisCache redisCache;

    /**
     * 获取所有用户
     * @return 用户列表
     */
    @Override
    public List<User> getAllUser() {
        return userMapper.selectList(null);
    }

    /**
     * 添加用户
     * @param registerRequest 注册请求体
     * @return 是否成功
     */
    @Override
    public Boolean addUser(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setEmail(registerRequest.getEmail());
        user.setNickname(registerRequest.getNickname() == null ? registerRequest.getUsername() : registerRequest.getNickname());
        return userMapper.insert(user) > 0;
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
     * 根据用户 ID 获取用户
     * @param userId 用户 ID
     * @return 用户
     */
    @Override
    public User getUser(Long userId) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId, userId);
        return userMapper.selectOne(wrapper);
    }

    /**
     * 获取当前用户
     * @return 用户
     */
    @Override
    public User getUser() {
        // 从 SecurityContextHolder 中获取用户信息
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUserDetails userDetails = (LoginUserDetails) authentication.getPrincipal();
        return userDetails.getUser();
    }

    /**
     * 用户是否存在
     * @param username 用户名
     * @return 是否存在
     */
    @Override
    public Boolean isUserExist(String username) {
        return getUser(username) != null;
    }

    /**
     * 用户是否存在
     * @param userId 用户 ID
     * @return 是否存在
     */
    @Override
    public Boolean isUserExist(Long userId) {
        return getUser(userId) != null;
    }

    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 用户
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = getUser(username);
        Preconditions.checkNotNull(user, "User not found");
        // TODO 查询用户权限
        List<String> permissions = new ArrayList<>(Arrays.asList("user:test", "user:read"));
        return new LoginUserDetails(user, permissions);
    }

    /**
     * 注册
     * @param registerRequest 注册请求体
     * @return 是否成功
     */
    @Override
    public boolean register(RegisterRequest registerRequest) {
        if (isUserExist(registerRequest.getUsername())) {
            throw new CustomException(USER_EXIST);
        }
        return addUser(registerRequest);
    }

    /**
     * 邮箱是否存在
     * @param email 邮箱
     * @return 是否存在
     */
    @Override
    public boolean isEmailExist(String email) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail, email);
        return userMapper.selectCount(wrapper) > 0;
    }

    @Value("${edge.email.verify.timeout}")
    private long emailVerifyTimeout;

    @Resource
    MailService mailService;

    @Override
    public boolean preRegister(EmailVerifyCodeRequest verifyRequest) {
        // 检查该邮箱是否在 redis 中存在
        String redisKey = "emailVerifyCode:" + verifyRequest.getEmail();
        if (redisCache.hasKey(redisKey)) {
            throw new CustomException(EMAIL_EXIST);
        }
        // 检查该邮箱是否已经注册
        if (isEmailExist(verifyRequest.getEmail())) {
            throw new CustomException(EMAIL_EXIST);
        }
        // 生成验证码
        String verifyCode = RandomUtil.randomNumbers(6);
        redisCache.set(redisKey, verifyCode);
        redisCache.expire(redisKey, emailVerifyTimeout, TimeUnit.MINUTES);
        mailService.sendEmailVerifyCode(verifyRequest.getEmail(), verifyCode, emailVerifyTimeout);
        return true;
    }
}
