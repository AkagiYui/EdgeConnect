package com.akagiyui.edgeconnect.service;

import com.akagiyui.edgeconnect.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author AkagiYui
 */

public interface UserService extends IService<User>, UserDetailsService {
    List<User> getAllUser();
    @Override
    UserDetails loadUserByUsername(String username);
}
