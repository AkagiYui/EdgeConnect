package com.akagiyui.edgeconnect.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author AkagiYui
 */
@Data
@AllArgsConstructor
public class CreateClientResponse {
    /**
     * 应用 ID
     */
    private String clientId;
    /**
     * 应用密钥
     */
    private String clientSecret;
}
