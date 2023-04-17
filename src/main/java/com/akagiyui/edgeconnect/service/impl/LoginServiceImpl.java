package com.akagiyui.edgeconnect.service.impl;

import com.akagiyui.edgeconnect.entity.LoginUserDetails;
import com.akagiyui.edgeconnect.entity.request.LoginRequest;
import com.akagiyui.edgeconnect.service.LoginService;
import com.akagiyui.edgeconnect.service.UserService;
import com.akagiyui.edgeconnect.utils.JWTUtils;
import com.google.common.base.Preconditions;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author AkagiYui
 */

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    UserService userService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public String login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，给出对应的提示
        Preconditions.checkNotNull(authenticate, "认证失败");
        //如果认证通过了，使用userid生成一个jwt jwt存入ResponseResult返回
        LoginUserDetails loginUserDetails = (LoginUserDetails) authenticate.getPrincipal();
        return JWTUtils.createJWT(loginUserDetails.getUser());
    }
}
