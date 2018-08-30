package com.demo.web.config;


import com.demo.web.interceptor.AppInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @program: parent
 * @description: 拦截器配置类
 * @author: zouweidong
 * @create: 2018-08-22 09:33
 **/
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

    @Bean
    public AppInterceptor appInterceptor()
    {
        return new AppInterceptor();
    }


    /**
     * 增加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(appInterceptor())
                //这里可以添加多个拦截器
                .addPathPatterns("/**")
                .excludePathPatterns("/todo/login");
    }
}
