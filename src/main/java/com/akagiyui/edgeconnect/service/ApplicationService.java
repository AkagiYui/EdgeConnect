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
    Long createApplication(CreateApplicationRequest createApplicationRequest, Long userId);

    /**
     * 判断应用是否存在
     * @param name 应用名称
     * @param userId 用户 ID
     * @return 是否存在
     */
    Boolean isApplicationExist(String name, Long userId);

    /**
     * 判断应用是否存在
     * @param appId 应用 ID
     * @param userId 用户 ID
     * @return 是否存在
     */
    Boolean isApplicationExist(Long appId, Long userId);

    /**
     * 删除应用
     * @param id 应用 ID
     * @return 是否成功
     */
    Boolean deleteApplication(Long id, Long userId);

    /**
     * 获取应用
     * @param id 应用 ID
     * @param userId 用户 ID
     * @return 应用
     */
    Application getApplication(Long id, Long userId);
}
