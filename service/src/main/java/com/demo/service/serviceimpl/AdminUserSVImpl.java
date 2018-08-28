package com.demo.service.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.demo.dao.AdminUserMapper;
import com.demo.entity.AdminUser;
import com.demo.service.iservice.IAdminUserSV;
import com.demo.utils.PasswordCryptoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private JedisPool jedisPool;

    @Override
    public AdminUser getAdminUser() {
        logger.info("获取单个用户信息");
        return adminUserMapper.selectByPrimaryKey(1);
    }

    @Override
    public AdminUser login(String mail, String password) throws Exception {
        logger.info("用户登录");
        AdminUser adminUser;
        Map<String, Object> params = new HashMap<>();
        params.put("eqMail", mail);
        List<AdminUser> adminUserList = adminUserMapper.selectByMap(params);
        if (!adminUserList.isEmpty()) {
            adminUser = adminUserList.get(0);
            logger.info(password + adminUser.getSalt());
            String encodePassword = PasswordCryptoUtil.encode(password + adminUser.getSalt());
            logger.info(encodePassword);
            logger.info(adminUser.getPassword());
            logger.info(""+adminUser.getPassword().equals(encodePassword));
            if (adminUser.getPassword().equals(encodePassword)) {
                logger.info("返回用户信息"+adminUser);
                return adminUser;
            } else {
                throw new Exception("密码错误");
            }
        } else {
            throw new Exception("用户不存在");
        }
    }
}
