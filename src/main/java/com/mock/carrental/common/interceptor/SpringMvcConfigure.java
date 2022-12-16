package com.mock.carrental.common.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfigure implements WebMvcConfigurer {

    @Autowired
    private SessionUserInterceptor sessionUserInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(this.sessionUserInterceptor).addPathPatterns("/**").excludePathPatterns(
        );
    }
}
