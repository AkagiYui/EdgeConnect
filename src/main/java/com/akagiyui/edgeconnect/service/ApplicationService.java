package com.akagiyui.edgeconnect.service;

import com.akagiyui.edgeconnect.entity.Application;
import com.akagiyui.edgeconnect.entity.request.CreateApplicationRequest;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 应用 Service 接口
 * @author AkagiYui
 */

public interface ApplicationService extends IService<Application> {
    /**
     * 创建应用
     * @return 是否成功
     */
    Long createApplication(CreateApplicationRequest createApplicationRequest);

    /**
     * 判断应用是否存在
     * @param name 应用名称
     * @param userId 用户 ID
     * @return 是否存在
     */
    Boolean isApplicationExist(String name, Long userId);
}
