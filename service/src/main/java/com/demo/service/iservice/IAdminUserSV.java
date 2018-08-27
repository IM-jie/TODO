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
     * 获取用户列表
     *
     * @return
     */
    List<AdminUser> adminUserList();
}
