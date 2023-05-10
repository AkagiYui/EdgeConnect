package com.akagiyui.edgeconnect.entity.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 创建应用请求体
 * @author AkagiYui
 */
@Data
public class CreateClientRequest {
    /**
     * 应用名称
     */
    @NotNull(message = "Client name cannot be empty")
    @Size(min = 1, max = 20, message = "Client name length must be between 1 and 20")
    private String name;
}
