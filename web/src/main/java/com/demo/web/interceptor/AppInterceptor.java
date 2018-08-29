package com.demo.web.interceptor;

import com.demo.entity.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @program: parent
 * @description: 拦截器
 * @author: zouweidong
 * @create: 2018-08-22 09:27
 **/
public class AppInterceptor implements HandlerInterceptor {

    @Autowired
    private JedisPool jedisPool;

//    private Jedis redis = jedisPool.getResource();
    /**
     * 在请求处理之前进行调用（Controller方法调用之前
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        return true;
//        //用户已登录
//        if (redis.get("loginUser") != null) {
//            httpServletRequest.setAttribute("loginUser",redis.get("loginUser"));
//            return true;
//        } else {//用户未登录，直接跳转登录页面
//            httpServletResponse.sendRedirect("/login");
//            return false;
//        }
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle被调用");

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion被调用");
    }
}
