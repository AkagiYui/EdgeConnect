package com.akagiyui.edgeconnect.service.impl;

import com.akagiyui.edgeconnect.entity.User;
import com.akagiyui.edgeconnect.mapper.UserMapper;
import com.akagiyui.edgeconnect.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author AkagiYui
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public List<User> getAllUser() {
        return getBaseMapper().selectList(null);
    }
}
