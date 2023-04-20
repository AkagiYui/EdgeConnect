package com.akagiyui.edgeconnect.service.impl;

import com.akagiyui.edgeconnect.entity.LoginUserDetails;
import com.akagiyui.edgeconnect.entity.request.LoginRequest;
import com.akagiyui.edgeconnect.exception.CustomException;
import com.akagiyui.edgeconnect.service.LoginService;
import com.akagiyui.edgeconnect.utils.JWTUtils;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import static com.akagiyui.edgeconnect.utils.ResponseEnum.UNAUTHORIZED;

/**
 * 登录服务实现类
 * @author AkagiYui
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    JWTUtils jwtUtils;

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
        //如果认证没通过，给出对应的提示
        if (authenticate == null) {
            throw new CustomException(UNAUTHORIZED);
        }
        //如果认证通过了，使用userid生成一个jwt jwt存入ResponseResult返回
        LoginUserDetails loginUserDetails = (LoginUserDetails) authenticate.getPrincipal();
        return jwtUtils.createJWT(loginUserDetails.getUser());
    }
}
