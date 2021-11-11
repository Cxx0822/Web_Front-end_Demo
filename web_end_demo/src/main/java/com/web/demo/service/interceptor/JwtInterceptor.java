package com.web.demo.service.interceptor;

import com.web.demo.service.utils.JWTUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 拦截器，解决每个接口都需要token导致的代码冗余问题
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头中的令牌
        String token = request.getHeader("token");

        // 验证令牌
        JWTUtil.verify(token);

        return true;
    }
}
