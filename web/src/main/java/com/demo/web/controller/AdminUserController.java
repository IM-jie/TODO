package com.demo.web.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.entity.common.Result;
import com.demo.service.iservice.IAdminUserSV;
import com.demo.utils.common.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: Spring-parent
 * @description:
 * @author: zouweidong
 * @create: 2018-08-17 16:43
 **/
@RestController
@RequestMapping("/todo/users")
public class AdminUserController {

    Logger logger = LoggerFactory.getLogger(AdminUserController.class);

    @Reference
    IAdminUserSV iAdminUserSV;

    @GetMapping("/getUser")
    public Result getUser()
    {
        logger.info("获取单个用户信息----------");
        return ResultUtil.success(iAdminUserSV.getAdminUser());
    }

}
