package com.demo.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.entity.AdminUser;
import com.demo.entity.common.Result;
import com.demo.entity.param.AdminUserAddParam;
import com.demo.service.iservice.IAdminUserSV;
import com.demo.utils.RandomStringUtil;
import com.demo.utils.common.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @DeleteMapping(value = "/{id}")
    public Result deleteAdminUser(@RequestAttribute(name = "loginUser") AdminUser loginUser, @PathVariable(name = "id") Integer id) {
        if (iAdminUserSV.deleteAdminUser(loginUser,id) != 0) {
            return ResultUtil.success(1003, "删除用户成功");
        } else {
            return ResultUtil.error(1004, "删除用户失败");
        }
    }

    @PostMapping(value = "")
    public Result addAdminUser(@RequestAttribute(name = "loginUser") AdminUser loginUser, @RequestBody @Valid AdminUserAddParam adminUserAddParam) {
        String username = adminUserAddParam.getUsername();
        String mail = adminUserAddParam.getMail();
        logger.info("添加用户" + adminUserAddParam.getUsername());
        if (!iAdminUserSV.isExistUsername(username) && !iAdminUserSV.isExistMail(mail)) {
            if (iAdminUserSV.addAdminUser(adminUserAddParam, loginUser) != 0) {
                return ResultUtil.success(1001, "添加用户成功");
            } else {
                return ResultUtil.error(1002, "添加用户失败");
            }
        } else {
            return ResultUtil.error(1003, "用户名或邮箱已存在");
        }
    }

    @PostMapping(value = "/{id}")
    public Result updatePassword(@PathVariable(name = "id") Integer id) {
        String password = RandomStringUtil.getRandomString(8);
        if (0 != iAdminUserSV.updatePassword(id, password)) {
            return ResultUtil.success(password);
        } else {
            return ResultUtil.error(1004,"重置密码失败");
        }
    }
}
