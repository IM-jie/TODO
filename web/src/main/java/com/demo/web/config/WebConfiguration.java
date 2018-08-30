package com.demo.web.config;


import com.demo.web.interceptor.AppInterceptor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @program: parent
 * @description: 配置类
 * @author: zouweidong
 * @create: 2018-08-22 09:33
 **/
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

    /**
     * 定义时间格式转换器
     */
    @Bean
    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        converter.setObjectMapper(mapper);
        return converter;
    }

    /**
     * 添加转换器
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //将我们定义的时间格式转换器添加到转换器列表中,
        //这样jackson格式化时候但凡遇到Date类型就会转换成我们定义的格式
        converters.add(jackson2HttpMessageConverter());
    }

    @Bean
    public AppInterceptor appInterceptor() {
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
