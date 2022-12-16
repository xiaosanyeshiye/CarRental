package com.mock.carrental.common.interceptor;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.mock.carrental.common.annotation.VerifyToken;
import com.mock.carrental.common.context.UserContext;
import com.mock.carrental.common.context.UserSession;
import com.mock.carrental.exception.BizException;
import com.mock.carrental.service.ICustomerService;
import com.mock.carrental.service.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

@Slf4j
@Component
public class SessionUserInterceptor implements HandlerInterceptor {

    @Autowired
    private ICustomerService customerService;

    private static final String HEADER_TOKEN = "TOKEN";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            if (!method.isAnnotationPresent(VerifyToken.class) && !method.getDeclaringClass().isAnnotationPresent(VerifyToken.class)) {
                return true;
            }

            String token = request.getHeader(HEADER_TOKEN);
            if (StrUtil.isBlank(token)) {
                throw new BizException("Please login first.");
            }
            CustomerDto customerDto = customerService.getCustomerByToken(token);
            if (DateUtil.compare(customerDto.getTokenExpireTime(), new Date()) <= 0) {
                throw new BizException("Please login first.");
            }
            UserContext.set(new UserSession(customerDto.getId(), customerDto.getCustomerName()));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.remove();
    }
}
