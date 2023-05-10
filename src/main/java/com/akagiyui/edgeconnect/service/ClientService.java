package com.akagiyui.edgeconnect.service;

import com.akagiyui.edgeconnect.entity.Client;
import com.akagiyui.edgeconnect.entity.request.CreateClientRequest;
import com.akagiyui.edgeconnect.entity.response.CreateClientResponse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 应用 Service 接口
 * @author AkagiYui
 */
public interface ClientService extends IService<Client> {
    /**
     * 创建应用
     * @return 是否成功
     */
    CreateClientResponse createClient(CreateClientRequest createClientRequest, Long userId);

    /**
     * 判断应用是否存在
     * @param name 应用名称
     * @param userId 用户 ID
     * @return 是否存在
     */
    Boolean isClientExistByName(String name, Long userId);

    /**
     * 判断应用是否存在
     *
     * @param clientId 应用 ID
     * @param userId   用户 ID
     * @return 是否存在
     */
    Boolean isClientExistByClientId(String clientId, Long userId);

    /**
     * 删除应用
     * @param id 应用 ID
     * @return 是否成功
     */
    Boolean deleteClient(String id, Long userId);

    /**
     * 获取应用
     * @param id 应用 ID
     * @param userId 用户 ID
     * @return 应用
     */
    Client getClient(String id, Long userId);
}
