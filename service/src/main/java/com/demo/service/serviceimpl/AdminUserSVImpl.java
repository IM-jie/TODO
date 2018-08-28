package com.demo.service.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.demo.dao.AdminUserMapper;
import com.demo.entity.AdminUser;
import com.demo.service.iservice.IAdminUserSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: parent
 * @description: 用户接口实现类
 * @author: zouweidong
 * @create: 2018-08-27 16:49
 **/
@Service
public class AdminUserSVImpl implements IAdminUserSV {

    Logger logger = LoggerFactory.getLogger(AdminUserSVImpl.class);
    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser getAdminUser()
    {
        logger.info("获取单个用户信息");
        return adminUserMapper.selectByPrimaryKey(1);
    }

    @Override
    public AdminUser getAdminUser(String mail, String password) {
        return null;
    }
}
