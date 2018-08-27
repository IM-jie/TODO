package com.demo.service.serviceimpl;

import com.demo.dao.AdminUserMapper;
import com.demo.entity.AdminUser;
import com.demo.service.iservice.IAdminUserSV;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: parent
 * @description: 用户接口实现类
 * @author: zouweidong
 * @create: 2018-08-27 16:49
 **/
public class AdminUserSVImpl implements IAdminUserSV {

    @Autowired
    private AdminUserMapper adminUserMapper;
    @Override
    public List<AdminUser> adminUserList()
    {
        List<AdminUser> adminUserList = new ArrayList<>();
        adminUserList.add(adminUserMapper.selectByPrimaryKey(1));
        return adminUserList;
    }
}
