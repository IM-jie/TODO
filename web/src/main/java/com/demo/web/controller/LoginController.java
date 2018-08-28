package com.demo.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.demo.entity.AdminUser;
import com.demo.entity.common.Result;
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

/**
 * @program: parent
 * @description: 登录接口
 * @author: zouweidong
 * @create: 2018-08-28 09:13
 **/
@RestController
@RequestMapping("/todo")
public class LoginController {

    @Reference
    IAdminUserSV iAdminUserSV;

    @Autowired
    private JedisPool jedisPool;

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public Result login(@RequestParam(value = "mail") String mail, @RequestParam(value = "password") String password) throws Exception {
        try {
            AdminUser adminUser = iAdminUserSV.login(mail, password);
            if (StringUtils.isEmpty(adminUser)) {
                return ResultUtil.error(1002, "邮箱或密码错误");
            } else {
                logger.info("用户登录成功");
                Jedis redis = jedisPool.getResource();
                String userJson = JSON.toJSONString(adminUser);
                redis.setex("loginUser", 60 * 30, userJson);
                return ResultUtil.success(1001,"登录成功");
            }
        } catch (Exception e) {
            logger.info(""+e);
            throw new Exception("系统错误");
        }
    }
}
