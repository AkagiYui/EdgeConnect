package com.akagiyui.edgeconnect.controller;

import com.akagiyui.edgeconnect.entity.Client;
import com.akagiyui.edgeconnect.entity.User;
import com.akagiyui.edgeconnect.entity.request.CreateClientRequest;
import com.akagiyui.edgeconnect.entity.response.ClientInfoResponse;
import com.akagiyui.edgeconnect.entity.response.CreateClientResponse;
import com.akagiyui.edgeconnect.exception.CustomException;
import com.akagiyui.edgeconnect.service.ClientService;
import com.akagiyui.edgeconnect.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.util.List;

import static com.akagiyui.edgeconnect.component.ResponseEnum.CLIENT_NOT_FOUND;

/**
 * 应用 Controller
 * @author AkagiYui
 */
@RestController
@RequestMapping("/client")
@Slf4j
public class ClientController {
    @Resource
    ClientService clientService;

    @Resource
    UserService userService;

    /**
     * 创建应用
     * @param createClientRequest 创建应用请求
     * @return 应用 ID
     */
    @PostMapping("")
    @PreAuthorize("isAuthenticated()")
    public CreateClientResponse createClient(@RequestBody @Valid CreateClientRequest createClientRequest) {
        User user = userService.getUser();
        return clientService.createClient(createClientRequest, user.getId());
    }

    /**
     * 获取应用信息
     * @param id 应用 ID
     * @return 应用信息
     */
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ClientInfoResponse getClientInfo(@PathVariable String id) {
        User user = userService.getUser();
        Client app = clientService.getClient(id, user.getId());
        if (app == null) {
            throw new CustomException(CLIENT_NOT_FOUND);
        }
        ClientInfoResponse clientInfoResponse = new ClientInfoResponse();
        clientInfoResponse.setClientId(app.getClientId());
        clientInfoResponse.setName(app.getName());
        clientInfoResponse.setCreateTime(app.getCreateTime());
        return clientInfoResponse;
    }

    /**
     * 删除应用
     * @param id 应用 ID
     * @return 是否删除成功
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public Boolean deleteClient(@PathVariable String id) {
        User user = userService.getUser();
        return clientService.deleteClient(id, user.getId());
    }

    /**
     * 获取应用列表
     * @return 应用列表
     */
    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    public List<ClientInfoResponse> getClientList() {
        User user = userService.getUser();
        List<Client> clientList = clientService.getClients(user.getId());
        List<ClientInfoResponse> clientInfoResponseList = new java.util.ArrayList<>();
        for (Client client : clientList) {
            ClientInfoResponse clientInfoResponse = new ClientInfoResponse();
            BeanUtils.copyProperties(client, clientInfoResponse);
            clientInfoResponseList.add(clientInfoResponse);
        }
        return clientInfoResponseList;
    }
}
