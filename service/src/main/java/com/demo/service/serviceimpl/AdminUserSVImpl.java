package com.demo.service.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.demo.dao.AdminUserMapper;
import com.demo.dao.TaskUserMapper;
import com.demo.entity.AdminUser;
import com.demo.entity.TaskUser;
import com.demo.service.iservice.IAdminUserSV;
import com.demo.utils.PasswordCryptoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
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
    private TaskUserMapper taskUserMapper;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public List<AdminUser> listAdminUser()
    {
        return adminUserMapper.selectByMap(new HashMap<>());
    }

    @Override
    public List<AdminUser> listAdminUserByTaskId(String taskId)
    {
        Map<String,Object> params = new HashMap<>();
        params.put("eqTaskId",taskId);
        List<TaskUser> taskUserList = taskUserMapper.selectByMap(params);
        List<AdminUser> adminUserList = new ArrayList<>();
        for(int i=0;i<taskUserList.size();i++)
        {
            Map<String,Object>param = new HashMap<>();
            param.put("eqUserId",taskUserList.get(i).getUserId());
            adminUserList.add(adminUserMapper.selectByMap(param).get(0));
        }
        return adminUserList;
    }

    @Override
    public AdminUser login(String mail, String password) {
        AdminUser adminUser;
        Map<String, Object> params = new HashMap<>();
        params.put("eqMail", mail);
        List<AdminUser> adminUserList = adminUserMapper.selectByMap(params);
        if (!adminUserList.isEmpty()) {
            adminUser = adminUserList.get(0);
            String encodePassword = PasswordCryptoUtil.encode((password + adminUser.getSalt()));
            if (adminUser.getPassword().equals(encodePassword)) {
                logger.info("登录用户信息" + adminUser);
                return adminUser;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
