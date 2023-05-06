package com.akagiyui.edgeconnect.entity.response;

import lombok.Data;

import java.util.Date;

/**
 * 应用信息响应体
 * @author AkagiYui
 */
@Data
public class ApplicationInfoResponse {
    /**
     * 应用 ID
     */
    private Long id;
    /**
     * 应用名称
     */
    private String name;
    /**
     * 创建时间
     */
    private Date createTime;
}
