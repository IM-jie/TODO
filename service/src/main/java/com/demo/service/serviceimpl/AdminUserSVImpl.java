package com.demo.service.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.demo.dao.AdminUserMapper;
import com.demo.dao.TaskRecordMapper;
import com.demo.dao.TaskUserMapper;
import com.demo.entity.AdminUser;
import com.demo.entity.TaskRecord;
import com.demo.entity.TaskUser;
import com.demo.entity.param.AdminUserAddParam;
import com.demo.entity.param.AdminUserUpdateParam;
import com.demo.service.iservice.IAdminUserSV;
import com.demo.service.iservice.ISendMailSV;
import com.demo.utils.NumberComponent;
import com.demo.utils.PasswordCryptoUtil;
import com.demo.utils.RandomStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
    private TaskRecordMapper taskRecordMapper;

    @Autowired
    private NumberComponent numberComponent;

    @Autowired
    private ISendMailSV iSendMailSV;

    @Override
    public List<AdminUser> listAdminUser() {
        Map<String, Object> params = new HashMap<>();
        params.put("neqStatus", 0);
        return adminUserMapper.selectByMap(params);
    }

    @Override
    public List<AdminUser> listAdminUserByTaskId(String taskId) {
        Map<String, Object> params = new HashMap<>();
        params.put("eqTaskId", taskId);
        List<TaskUser> taskUserList = taskUserMapper.selectByMap(params);
        List<AdminUser> adminUserList = new ArrayList<>();
        for (int i = 0; i < taskUserList.size(); i++) {
            Map<String, Object> param = new HashMap<>();
            param.put("eqUserId", taskUserList.get(i).getUserId());
            params.put("neqStatus", 0);
            adminUserList.add(adminUserMapper.selectByMap(param).get(0));
        }
        return adminUserList;
    }

    @Override
    public int deleteAdminUser(AdminUser loginUser, Integer id) {
        Map<String, Object> params = new HashMap<>();
        params.put("eqId", id);
        params.put("status", 0);
        adminUserMapper.updateByMap(params);
        //添加操作记录
        TaskRecord taskRecord = new TaskRecord();
        String recordId = numberComponent.getGuid();
        taskRecord.setRecordId(recordId);
        taskRecord.setOperateType(0);
        taskRecord.setTaskId(adminUserMapper.selectByPrimaryKey(id).getUserId());
        taskRecord.setOperator(loginUser.getUsername());
        taskRecord.setOperatorId(loginUser.getUserId());
        taskRecord.setOperate("关闭了账号【" + adminUserMapper.selectByPrimaryKey(id).getUsername() + "】");
        taskRecord.setOperateTime(new Date(System.currentTimeMillis()));
        taskRecord.setStatus(1);
        return taskRecordMapper.insert(taskRecord);
    }

    @Override
    public int addAdminUser(AdminUserAddParam adminUserAddParam, AdminUser loginUser) {
        AdminUser adminUser = new AdminUser();
        String userId = numberComponent.getGuid();
        adminUser.setUserId(userId);
        adminUser.setUsername(adminUserAddParam.getUsername());
        adminUser.setMail(adminUserAddParam.getMail());
        String salt = RandomStringUtil.getRandomString(8);
        adminUser.setSalt(salt);
        adminUser.setPassword(PasswordCryptoUtil.encode(adminUserAddParam.getPassword() + salt));
        adminUser.setPermissionId(1);
        adminUser.setStatus(1);
        adminUser.setCreateTime(new Date(System.currentTimeMillis()));
        adminUser.setCreator(loginUser.getUsername());
        adminUserMapper.insert(adminUser);
        //添加操作记录
        TaskRecord taskRecord = new TaskRecord();
        String recordId = numberComponent.getGuid();
        taskRecord.setRecordId(recordId);
        taskRecord.setOperateType(0);
        taskRecord.setTaskId(loginUser.getUserId());
        taskRecord.setOperator(adminUserAddParam.getUsername());
        taskRecord.setOperatorId(userId);
        taskRecord.setOperate("加入了TeamToy");
        taskRecord.setOperateTime(new Date(System.currentTimeMillis()));
        taskRecord.setStatus(1);
        return taskRecordMapper.insert(taskRecord);
    }

    @Override
    public int resetPassword(Integer id, String password) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(id);
        String encodePassword = PasswordCryptoUtil.encode(password + adminUser.getSalt());
        adminUser.setPassword(encodePassword);
        String mail = adminUser.getMail();
        String subject = "TeamToy密码重置结果";
        String content = "您的新密码为：" + password;
        iSendMailSV.sendSimpleMail(mail, subject, content);
        return adminUserMapper.updateByPrimaryKeySelective(adminUser);
    }

    @Override
    public int updatePassword(Integer id, String password, String newPassword) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(id);
        if (!PasswordCryptoUtil.encode(password + adminUser.getSalt()).equals(adminUser.getPassword())) {
            return -1;
        } else {
            adminUser.setPassword(PasswordCryptoUtil.encode(newPassword + adminUser.getSalt()));
            return adminUserMapper.updateByPrimaryKeySelective(adminUser);
        }
    }

    @Override
    public int updatePermission(Integer id, Integer permission, AdminUser loginUser) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(id);
        adminUser.setPermissionId(permission);
        adminUserMapper.updateByPrimaryKeySelective(adminUser);
        //添加更改用户权限操作记录
        TaskRecord taskRecord = new TaskRecord();
        String recordId = numberComponent.getGuid();
        taskRecord.setRecordId(recordId);
        taskRecord.setOperateType(0);
        taskRecord.setTaskId(adminUser.getUserId());
        taskRecord.setOperator(loginUser.getUsername());
        taskRecord.setOperatorId(loginUser.getUserId());
        if (permission == 0) {
            taskRecord.setOperate("将账号【" + adminUser.getUsername() + "】的等级调整为超级管理员");

        } else {
            taskRecord.setOperate("将账号【" + adminUser.getUsername() + "】的等级调整为普通成员");
        }
        taskRecord.setOperateTime(new Date(System.currentTimeMillis()));
        taskRecord.setStatus(1);
        return taskRecordMapper.insert(taskRecord);
    }

    @Override
    public int updateAdminUser(Integer id, AdminUserUpdateParam adminUserUpdateParam) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(id);
        adminUser.setMail(adminUserUpdateParam.getMail());
        adminUser.setPhone(adminUserUpdateParam.getPhone());
        adminUser.setPhoneCopy(adminUserUpdateParam.getPhoneCopy());
        adminUser.setBlogName(adminUserUpdateParam.getBlogName());
        adminUser.setEmpNo(adminUserUpdateParam.getEmpNo());
        adminUser.setRemark(adminUserUpdateParam.getRemark());
        return adminUserMapper.updateByPrimaryKeySelective(adminUser);
    }

    @Override
    public boolean isExistUsername(String username) {
        Map<String, Object> params = new HashMap<>();
        params.put("eqUserName", username);
        params.put("neqStatus", 0);
        if (adminUserMapper.selectByMap(params).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isExistMail(String mail) {
        Map<String, Object> params = new HashMap<>();
        params.put("eqMail", mail);
        params.put("neqStatus", 0);
        if (adminUserMapper.selectByMap(params).isEmpty()) {
            return false;
        } else {
            return true;
        }
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
