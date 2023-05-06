package com.akagiyui.edgeconnect.service.impl;

import com.akagiyui.edgeconnect.entity.Application;
import com.akagiyui.edgeconnect.entity.request.CreateApplicationRequest;
import com.akagiyui.edgeconnect.exception.CustomException;
import com.akagiyui.edgeconnect.mapper.ApplicationMapper;
import com.akagiyui.edgeconnect.service.ApplicationService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.akagiyui.edgeconnect.component.ResponseEnum.APPLICATION_EXIST;
import static com.akagiyui.edgeconnect.component.ResponseEnum.APPLICATION_NOT_FOUND;

/**
 * 应用服务 实现
 * @author AkagiYui
 */
@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements ApplicationService {
    @Resource
    ApplicationMapper applicationMapper;

    @Override
    public Long createApplication(CreateApplicationRequest createApplicationRequest, Long userId) {
        if (isApplicationExist(createApplicationRequest.getName(), userId)) {
            throw new CustomException(APPLICATION_EXIST);
        }
        Application application = new Application();
        application.setOwner(userId);
        application.setName(createApplicationRequest.getName());
        applicationMapper.insert(application);
        return application.getId();
    }

    @Override
    public Boolean isApplicationExist(String name, Long userId) {
        LambdaQueryWrapper<Application> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Application::getName, name);
        lambdaQueryWrapper.eq(Application::getOwner, userId);
        return applicationMapper.selectCount(lambdaQueryWrapper) > 0;
    }

    @Override
    public Boolean isApplicationExist(Long appId, Long userId) {
        LambdaQueryWrapper<Application> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Application::getId, appId);
        lambdaQueryWrapper.eq(Application::getOwner, userId);
        return applicationMapper.selectCount(lambdaQueryWrapper) > 0;
    }

    @Override
    public Boolean deleteApplication(Long id, Long userId) {
        if (!isApplicationExist(id, userId)) {
            throw new CustomException(APPLICATION_NOT_FOUND);
        }
        Application application = new Application();
        application.setId(id);
        application.setIsDeleted(true);
        applicationMapper.updateById(application);
        return true;
    }

    @Override
    public Application getApplication(Long id, Long userId) {
        LambdaQueryWrapper<Application> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Application::getId, id);
        lambdaQueryWrapper.eq(Application::getOwner, userId);
        return applicationMapper.selectOne(lambdaQueryWrapper);
    }
}
