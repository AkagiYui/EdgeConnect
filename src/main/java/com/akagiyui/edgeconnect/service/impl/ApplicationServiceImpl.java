package com.akagiyui.edgeconnect.service.impl;

import com.akagiyui.edgeconnect.entity.Application;
import com.akagiyui.edgeconnect.entity.User;
import com.akagiyui.edgeconnect.entity.request.CreateApplicationRequest;
import com.akagiyui.edgeconnect.exception.CustomException;
import com.akagiyui.edgeconnect.mapper.ApplicationMapper;
import com.akagiyui.edgeconnect.service.ApplicationService;
import com.akagiyui.edgeconnect.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.akagiyui.edgeconnect.component.ResponseEnum.APPLICATION_EXIST;

/**
 * 应用服务 实现
 * @author AkagiYui
 */
@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements ApplicationService {
    @Resource
    ApplicationMapper applicationMapper;

    @Resource
    UserService userService;

    @Override
    public Long createApplication(CreateApplicationRequest createApplicationRequest) {
        User user = userService.getUser();
        if (isApplicationExist(createApplicationRequest.getName(), user.getId())) {
            throw new CustomException(APPLICATION_EXIST);
        }
        Application application = new Application();
        application.setOwner(user.getId());
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
}
