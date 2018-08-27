package com.demo.web.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.entity.AdminUser;
import com.demo.entity.common.Result;
import com.demo.service.iservice.IAdminUserSV;
import com.demo.utils.common.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: Spring-parent
 * @description:
 * @author: zouweidong
 * @create: 2018-08-17 16:43
 **/
@RestController
@ResponseBody
@RequestMapping("/todo")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Reference
    IAdminUserSV iAdminUserSV;

    @GetMapping("/getUser")
    public Result getUser()
    {
        List<AdminUser> adminUserList = iAdminUserSV.adminUserList();
        return ResultUtil.success(adminUserList);
    }

}
