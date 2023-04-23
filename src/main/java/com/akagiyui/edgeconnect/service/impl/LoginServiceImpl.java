package com.akagiyui.edgeconnect.service.impl;

import com.akagiyui.edgeconnect.component.RedisCache;
import com.akagiyui.edgeconnect.entity.LoginUserDetails;
import com.akagiyui.edgeconnect.entity.User;
import com.akagiyui.edgeconnect.entity.request.LoginRequest;
import com.akagiyui.edgeconnect.exception.CustomException;
import com.akagiyui.edgeconnect.service.LoginService;
import com.akagiyui.edgeconnect.component.JWTUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.akagiyui.edgeconnect.component.ResponseEnum.UNAUTHORIZED;

/**
 * 登录服务实现类
 * @author AkagiYui
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    JWTUtils jwtUtils;

    @Resource
    RedisCache redisCache;

    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 登录
     * @param request 登录请求体
     * @return jwt 字符串
     */
    @Override
    public String login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，抛出异常
        if (authenticate == null) {
            throw new CustomException(UNAUTHORIZED);
        }
        //如果认证通过了，使用userid生成一个jwt jwt存入ResponseResult返回
        LoginUserDetails loginUserDetails = (LoginUserDetails) authenticate.getPrincipal();
        User user = loginUserDetails.getUser();
        redisCache.set(String.format("user:%s", user.getId()), loginUserDetails);
        return jwtUtils.createJWT(user);
    }
}
