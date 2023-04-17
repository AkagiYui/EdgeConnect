package com.akagiyui.edgeconnect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author AkagiYui
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 禁用basic明文验证
                .httpBasic().disable()

                // 前后端分离架构不需要csrf保护
                .csrf().disable()
                // 前后端分离是无状态的，不需要session了，直接禁用。
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 禁用默认登录页
                .formLogin().disable()
                // 禁用默认登出页
                .logout().disable()
                // 放行 login
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers("/user/login").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();

                // 设置异常的EntryPoint，如果不设置，默认使用Http403ForbiddenEntryPoint
//                .exceptionHandling(exceptions -> exceptions.authenticationEntryPoint(invalidAuthenticationEntryPoint))
//                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
//                        // 允许所有OPTIONS请求
//                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                        // 允许直接访问授权登录接口
//                        .requestMatchers(HttpMethod.POST, "/web/authenticate").permitAll()
//                        // 允许 SpringMVC 的默认错误地址匿名访问
//                        .requestMatchers("/error").permitAll()
//                        // 其他所有接口必须有Authority信息，Authority在登录成功后的UserDetailsImpl对象中默认设置“ROLE_USER”
//                        //.requestMatchers("/**").hasAnyAuthority("ROLE_USER")
//                        // 允许任意请求被已登录用户访问，不检查Authority
//                        .anyRequest().authenticated())
//                .authenticationProvider(authenticationProvider())
//                // 加我们自定义的过滤器，替代UsernamePasswordAuthenticationFilter
//                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
