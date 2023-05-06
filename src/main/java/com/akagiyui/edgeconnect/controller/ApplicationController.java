package com.akagiyui.edgeconnect.controller;

import com.akagiyui.edgeconnect.entity.Application;
import com.akagiyui.edgeconnect.entity.User;
import com.akagiyui.edgeconnect.entity.request.CreateApplicationRequest;
import com.akagiyui.edgeconnect.entity.response.ApplicationInfoResponse;
import com.akagiyui.edgeconnect.exception.CustomException;
import com.akagiyui.edgeconnect.service.ApplicationService;
import com.akagiyui.edgeconnect.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static com.akagiyui.edgeconnect.component.ResponseEnum.APPLICATION_NOT_FOUND;

/**
 * 应用 Controller
 * @author AkagiYui
 */
@RestController
@RequestMapping("/app")
@Slf4j
public class ApplicationController {
    @Resource
    ApplicationService applicationService;

    @Resource
    UserService userService;

    /**
     * 创建应用
     * @param createApplicationRequest 创建应用请求
     * @return 应用 ID
     */
    @PostMapping("")
    @PreAuthorize("isAuthenticated()")
    public Long createApplication(@RequestBody @Valid CreateApplicationRequest createApplicationRequest) {
        User user = userService.getUser();
        return applicationService.createApplication(createApplicationRequest, user.getId());
    }

    /**
     * 获取应用信息
     * @param id 应用 ID
     * @return 应用信息
     */
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ApplicationInfoResponse getApplicationInfo(@PathVariable Long id) {
        User user = userService.getUser();
        Application app = applicationService.getApplication(id, user.getId());
        if (app == null) {
            throw new CustomException(APPLICATION_NOT_FOUND);
        }
        ApplicationInfoResponse applicationInfoResponse = new ApplicationInfoResponse();
        applicationInfoResponse.setId(app.getId());
        applicationInfoResponse.setName(app.getName());
        applicationInfoResponse.setCreateTime(app.getCreateTime());
        return applicationInfoResponse;
    }

    /**
     * 删除应用
     * @param id 应用 ID
     * @return 是否删除成功
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public Boolean deleteApplication(@PathVariable Long id) {
        User user = userService.getUser();
        return applicationService.deleteApplication(id, user.getId());
    }
}
