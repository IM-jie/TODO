package com.demo.service.iservice;

import com.demo.entity.AdminUser;

import java.util.List;

/**
 * @program: parent
 * @description: 用户接口类
 * @author: zouweidong
 * @create: 2018-08-27 16:48
 **/
public interface IAdminUserSV {

    /**
     * 根据邮箱和密码获取用户信息
     * @param mail
     * @param password
     * @return
     * @throws Exception
     */
    AdminUser login(String mail,String password);

    /**
     * 获取用户列表
     * @return
     */
    List<AdminUser> listAdminUser();

    /**
     * 获取任务关注用户列表
     * @return
     */
    List<AdminUser> listAdminUserByTaskId(String taskId);
}
