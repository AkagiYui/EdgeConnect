package com.akagiyui.edgeconnect.filter;

import com.akagiyui.edgeconnect.component.JWTUtils;
import com.akagiyui.edgeconnect.component.RedisCache;
import com.akagiyui.edgeconnect.entity.LoginUserDetails;
import com.akagiyui.edgeconnect.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT 认证过滤器
 * @author AkagiYui
 */
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Resource
    JWTUtils jwtUtils;

    @Resource
    UserService userService;

    @Resource
    RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        // 获取 Token
        String rawToken = request.getHeader("Authorization");
        if (StringUtils.hasText(rawToken) && rawToken.startsWith("Bearer ")) {
            String token = rawToken.substring(7);
            // 验证 Token
            if (jwtUtils.verifyJWT(token)) {
                // 获取用户 ID
                Long userId = jwtUtils.getUserId(token);
                if (userId != null && userService.isUserExist(userId)) {
                    // 从 redis 中获取用户信息
                    String redisKey = "user:" + userId;
                    LoginUserDetails userDetails = redisCache.get(redisKey);
                    // 在 redis 中没有找到用户信息 TODO 从数据库中获取用户信息
                    if (userDetails != null) {
                        // 放入 Spring Security 上下文
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            }

        }
        filterChain.doFilter(request, response);
    }
}

