package com.demo.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.demo.entity.AdminUser;
import com.demo.entity.constants.CommonConstants;
import com.demo.utils.PasswordCryptoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: parent
 * @description: 拦截器
 * @author: zouweidong
 * @create: 2018-08-22 09:27
 **/
@Component
public class AppInterceptor implements HandlerInterceptor {

    private static Logger LOGGER = LoggerFactory.getLogger(AppInterceptor.class);

    @Autowired
    private JedisPool jedisPool;

    /**
     * 在请求处理之前进行调用（Controller方法调用之前
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies == null) {
//            LOGGER.info("cookie为空");
//            Result result=new Result(1,"cookie为空");
//            httpServletResponse.setCharacterEncoding("UTF-8");
//            httpServletResponse.setContentType("application/json; charset=utf-8");
//            httpServletResponse.getWriter().write(result.toString());
//            return false;
            AdminUser loginUser = new AdminUser();
            loginUser.setUsername("admin");
            loginUser.setUserId("12345678");
            loginUser.setPermissionId(0);
            String string=PasswordCryptoUtil.encode("userinfo:admin");
            Cookie cookie = new Cookie("Info_side", string);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 30);
            httpServletRequest.setAttribute("loginUser", loginUser);
            return true;
        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(CommonConstants.COOKIE_KEY)) {
                    String str = PasswordCryptoUtil.decode(cookie.getValue());
                    LOGGER.info("Str--->"+str);
                    Jedis redis = jedisPool.getResource();
                    String user = redis.get(str);
                    AdminUser adminUser=JSONObject.parseObject(user,AdminUser.class);
                    httpServletRequest.setAttribute("loginUser", adminUser);
                }
            }
            return true;
        }
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
