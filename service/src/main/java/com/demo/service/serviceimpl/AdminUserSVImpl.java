package com.demo.service.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.demo.dao.AdminUserMapper;
import com.demo.dao.TaskUserMapper;
import com.demo.entity.AdminUser;
import com.demo.entity.TaskUser;
import com.demo.entity.param.AdminUserAddParam;
import com.demo.service.iservice.IAdminUserSV;
import com.demo.utils.NumberComponent;
import com.demo.utils.PasswordCryptoUtil;
import com.demo.utils.RandomStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;

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
    NumberComponent numberComponent;

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
    public int deleteAdminUser(Integer id)
    {
        Map<String,Object>params = new HashMap<>();
        params.put("eqId",id);
        params.put("status",0);
        return adminUserMapper.updateByMap(params);
    }

    @Override
    public int addAdminUser(AdminUserAddParam adminUserAddParam,AdminUser loginUser)
    {
        AdminUser adminUser = new AdminUser();
        String userId = numberComponent.getGuid();
        adminUser.setUserId(userId);
        adminUser.setUsername(adminUserAddParam.getUsername());
        adminUser.setMail(adminUserAddParam.getMail());
        String password = adminUserAddParam.getPassword();
        String salt = RandomStringUtil.getRandomString(8);
        adminUser.setSalt(salt);
        adminUser.setPassword(PasswordCryptoUtil.encode(password+salt));
        adminUser.setPermissionId(1);
        adminUser.setStatus(1);
        adminUser.setCreateTime(new Date(System.currentTimeMillis()));
        adminUser.setCreator(loginUser.getUsername());
        return adminUserMapper.insertSelective(adminUser);
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
