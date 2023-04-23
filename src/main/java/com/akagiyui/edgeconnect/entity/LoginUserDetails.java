package com.akagiyui.edgeconnect.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * 登录用户详情
 * @author AkagiYui
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDetails implements UserDetails, Serializable {
    User user;

    /**
     * 获取用户权限
     * @return 用户权限集合
     */
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * 获取用户密码
     * @return 用户密码
     */
    @Override
    @JsonIgnore
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * 获取用户名
     * @return 用户名
     */
    @Override
    @JsonIgnore
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * 用户账号是否未过期
     * @return 用户账号是否未过期
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 用户账号是否未被锁定
     * @return 用户账号是否未被锁定
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 用户密码是否未过期
     * @return 用户密码是否未过期
     */
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 用户是否可用
     * @return 用户是否可用
     */
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return !user.getIsDisabled();
    }
}
