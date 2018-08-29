package com.demo.web.config;


import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.demo.web.interceptor.AppInterceptor;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: parent
 * @description: 拦截器配置类
 * @author: zouweidong
 * @create: 2018-08-22 09:33
 **/
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

    /**
     * 增加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AppInterceptor())
                //这里可以添加多个拦截器
                .addPathPatterns("/todo/login");
    }
}
