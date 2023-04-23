package com.akagiyui.edgeconnect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security 配置
 * @author AkagiYui
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 密码加密器
     * @return 密码加密器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证管理器
     * @return 认证管理器
     * @throws Exception 异常
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Spring Security 配置
     * @param http HttpSecurity
     * @throws Exception 异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 禁用basic明文验证
                .httpBasic().disable()

                // 前后端分离架构不需要csrf保护
                .csrf().disable()

                // 前后端分离是无状态的，不需要session了，直接禁用。
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                // 禁用默认登录页
                .formLogin().disable()
                // 禁用默认登出页
                .logout().disable()

                .exceptionHandling(exceptions -> {
                    // 使用匿名内部类
                    exceptions.authenticationEntryPoint((request, response, authException) -> {
                        // 未认证，返回401
                        response.sendError(401);
                    });
                })

                // 路由权限配置
                .authorizeRequests()
                    // 允许所有OPTIONS请求
                    .antMatchers(HttpMethod.OPTIONS).permitAll()
                    // 放行登录接口
                    .antMatchers(HttpMethod.POST, "/user/login").permitAll()
                    // 其他所有接口必须接受认证
                    .anyRequest().fullyAuthenticated();

        // 允许跨域
        http.cors();
//                .authenticationProvider(authenticationProvider())
//                // 加我们自定义的过滤器，替代UsernamePasswordAuthenticationFilter
//                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
