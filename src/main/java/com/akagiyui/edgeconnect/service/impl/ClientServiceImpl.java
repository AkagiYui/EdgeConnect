package com.akagiyui.edgeconnect.service.impl;

import com.akagiyui.edgeconnect.entity.Client;
import com.akagiyui.edgeconnect.entity.request.CreateClientRequest;
import com.akagiyui.edgeconnect.entity.response.CreateClientResponse;
import com.akagiyui.edgeconnect.exception.CustomException;
import com.akagiyui.edgeconnect.mapper.ClientMapper;
import com.akagiyui.edgeconnect.service.ClientService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import static com.akagiyui.edgeconnect.component.ResponseEnum.CLIENT_EXIST;
import static com.akagiyui.edgeconnect.component.ResponseEnum.CLIENT_NOT_FOUND;

/**
 * 应用服务 实现
 * @author AkagiYui
 */
@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements ClientService {
    @Resource
    ClientMapper clientMapper;

    @Override
    public CreateClientResponse createClient(CreateClientRequest createClientRequest, Long userId) {
        if (isClientExistByName(createClientRequest.getName(), userId)) {
            throw new CustomException(CLIENT_EXIST);
        }
        Client client = new Client();
        client.setOwner(userId);
        client.setName(createClientRequest.getName());

        // 生成应用 ID 和应用密钥
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);

        String clientId = Base64.getEncoder().encodeToString(bytes);
        String clientSecret = Base64.getEncoder().encodeToString(bytes);

        client.setClientId(clientId);
        client.setClientSecret(clientSecret);

        save(client);
        return new CreateClientResponse(clientId, clientSecret);
    }

    @Override
    public Boolean isClientExistByName(String name, Long userId) {
        LambdaQueryWrapper<Client> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Client::getName, name);
        lambdaQueryWrapper.eq(Client::getOwner, userId);
        return clientMapper.selectCount(lambdaQueryWrapper) > 0;
    }

    @Override
    public Boolean isClientExistByClientId(String clientId, Long userId) {
        LambdaQueryWrapper<Client> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Client::getClientId, clientId);
        lambdaQueryWrapper.eq(Client::getOwner, userId);
        return clientMapper.selectCount(lambdaQueryWrapper) > 0;
    }

    @Override
    public Boolean deleteClient(String clientId, Long userId) {
        if (!isClientExistByClientId(clientId, userId)) {
            throw new CustomException(CLIENT_NOT_FOUND);
        }
        Client client = new Client();
        client.setClientId(clientId);
        client.setIsDeleted(true);
        clientMapper.updateById(client);
        return true;
    }

    @Override
    public Client getClient(String id, Long userId) {
        LambdaQueryWrapper<Client> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Client::getClientId, id);
        lambdaQueryWrapper.eq(Client::getOwner, userId);
        return clientMapper.selectOne(lambdaQueryWrapper);
    }

    @Override
    public List<Client> getClients(Long userId) {
        LambdaQueryWrapper<Client> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Client::getOwner, userId);
        return clientMapper.selectList(lambdaQueryWrapper);
    }
}
