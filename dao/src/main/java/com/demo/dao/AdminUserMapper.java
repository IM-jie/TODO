package com.demo.dao;

import com.demo.entity.AdminUser;

import java.util.List;
import java.util.Map;

/**
 * @author mac
 */
public interface AdminUserMapper {
    /**
     * 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入
     * @param record
     * @return
     */
    int insert(AdminUser record);

    /**
     * 插入
     * @param record
     * @return
     */
    int insertSelective(AdminUser record);

    /**
     * 查询
     * @param id
     * @return
     */
    AdminUser selectByPrimaryKey(Integer id);

    /**
     * 查询列表by map
     * @param params
     * @return
     */
    List<AdminUser> selectByMap(Map<String, Object> params);

    /**
     * 通过用户名查询用户信息
     * @param userName
     * @return
     */
    AdminUser selectByUserName(String userName);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(AdminUser record);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(AdminUser record);

    /**
     * 更新by map
     * @param params
     * @return
     */
    int updateByMap(Map<String, Object> params);
}