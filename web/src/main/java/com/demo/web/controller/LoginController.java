package com.demo.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.demo.entity.AdminUser;
import com.demo.entity.common.Result;
import com.demo.entity.constants.CacheConstants;
import com.demo.entity.constants.CommonConstants;
import com.demo.service.iservice.IAdminUserSV;
import com.demo.utils.PasswordCryptoUtil;
import com.demo.utils.common.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: parent
 * @description: 登录接口
 * @author: zouweidong
 * @create: 2018-08-28 09:13
 **/
@RestController
@RequestMapping("/mytodo")
public class LoginController {

    @Reference
    IAdminUserSV iAdminUserSV;

    @Autowired
    private JedisPool jedisPool;

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public Result login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,@RequestParam(value = "mail") String mail, @RequestParam(value = "password") String password) throws Exception {
        try {
            AdminUser adminUser = iAdminUserSV.login(mail, password);
            if (StringUtils.isEmpty(adminUser)) {
                return ResultUtil.error(1002, "邮箱或密码错误");
            } else {
                logger.info("用户登录成功");
                String userJson = JSON.toJSONString(adminUser);
                Jedis redis = jedisPool.getResource();
                String userKey = CacheConstants.USER_INFO_KEY+adminUser.getUsername();
                redis.setex(userKey, 60 * 30, userJson);
                Cookie cookie=new Cookie(CommonConstants.COOKIE_KEY,PasswordCryptoUtil.encode(userKey));
                cookie.setPath("/");
                cookie.setMaxAge(60*30);
                httpServletResponse.addCookie(cookie);
                return ResultUtil.success(1001,"登录成功");
            }
        } catch (Exception e) {
            logger.info("系统出错"+e);
            throw new Exception("系统错误");
        }
    }
}
