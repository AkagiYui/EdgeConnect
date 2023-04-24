package com.akagiyui.edgeconnect.handler;

import com.akagiyui.edgeconnect.entity.ResponseResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.akagiyui.edgeconnect.component.ResponseEnum.UNAUTHORIZED;

/**
 * 无权限处理器
 * @author AkagiYui
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 未授权，返回403
        ResponseResult<?> result = ResponseResult.response(UNAUTHORIZED);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result.toString());
    }
}
