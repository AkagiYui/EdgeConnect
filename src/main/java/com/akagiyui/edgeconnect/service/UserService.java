package com.akagiyui.edgeconnect.service;

import com.akagiyui.edgeconnect.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author AkagiYui
 */

public interface UserService extends IService<User> {

    List<User> getAllUser();

}
