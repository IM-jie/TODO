package com.demo.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.entity.AdminUser;
import com.demo.entity.common.Result;
import com.demo.service.iservice.IAdminUserSV;
import com.demo.utils.common.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: Spring-parent
 * @description:
 * @author: zouweidong
 * @create: 2018-08-17 16:43
 **/
@RestController
@RequestMapping(value = "/mytodo/users")
public class AdminUserController {

    Logger logger = LoggerFactory.getLogger(AdminUserController.class);

    @Reference
    IAdminUserSV iAdminUserSV;

    /**
     * @apiDescription 获取用户列表
     * Author: zouweidong
     * @api {get} /mytodo/users 获取用户列表
     * @apiName listAdminUser
     * @apiGroup adminusers
     * @apiVersion 0.0.1
     * @apiSuccess (返回结果参数说明){Integer} code  返回状态码
     * @apiSuccess (返回结果参数说明){String} msg  返回信息
     * @apiSuccess (返回结果参数说明){JSONObject} data  处理返回信息
     * @apiSuccessExample json  返回样例
     */
    @GetMapping(value = "")
    public Result listAdminUser() {
        List<AdminUser> adminUserList = iAdminUserSV.listAdminUser();
        return ResultUtil.success(adminUserList);
    }

    @GetMapping(value = "/{taskId}")
    public Result listAdminUserByTaskId(@PathVariable(name = "taskId") String taskId) {
        List<AdminUser> adminUserList = iAdminUserSV.listAdminUserByTaskId(taskId);
        return ResultUtil.success(adminUserList);
    }
}
