package com.akagiyui.edgeconnect.service;

import com.akagiyui.edgeconnect.entity.request.LoginRequest;

/**
 * @author AkagiYui
 */

public interface LoginService {
    String login(LoginRequest user);
}
