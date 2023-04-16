package com.akagiyui.edgeconnect.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author AkagiYui
 */

@Data
public class User {
    Long id;
    String username;
    String password;
    String nickname;
    String email;
    Boolean isDisabled;
    Timestamp createTime;
}
