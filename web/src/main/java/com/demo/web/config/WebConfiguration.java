package com.demo.web.config;


import com.demo.web.interceptor.AppInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import redis.clients.jedis.JedisPool;

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
                .addPathPatterns("/**")
                .excludePathPatterns("/todo/login");
    }
}
