package com.demo.service.iservice;

import com.demo.entity.AdminUser;
import com.demo.entity.param.AdminUserAddParam;

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
     * @param taskId
     * @return
     */
    List<AdminUser> listAdminUserByTaskId(String taskId);

    /**
     * 根据主键删除用户
     * @param id
     * @return
     */
    int deleteAdminUser(AdminUser loginUser,Integer id);

    /**
     * 添加用户
     * @param adminUserAddParam
     * @param loginUser
     * @return
     */
    int addAdminUser(AdminUserAddParam adminUserAddParam,AdminUser loginUser);

    boolean isExistUsername(String username);
    boolean isExistMail(String mail);

    int updatePassword(Integer id,String password);
}
