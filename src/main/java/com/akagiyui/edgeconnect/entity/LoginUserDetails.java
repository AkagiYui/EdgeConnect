package com.akagiyui.edgeconnect.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 登录用户详情
 * @author AkagiYui
 */
@Data
@AllArgsConstructor
public class LoginUserDetails implements UserDetails {
    User user;

    /**
     * 获取用户权限
     * @return 用户权限集合
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * 获取用户密码
     * @return 用户密码
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * 获取用户名
     * @return 用户名
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * 用户账号是否未过期
     * @return 用户账号是否未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 用户账号是否未被锁定
     * @return 用户账号是否未被锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 用户密码是否未过期
     * @return 用户密码是否未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 用户是否可用
     * @return 用户是否可用
     */
    @Override
    public boolean isEnabled() {
        return !user.getIsDisabled();
    }
}
