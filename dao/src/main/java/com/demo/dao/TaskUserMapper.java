package com.demo.dao;

import com.demo.entity.AdminUser;
import com.demo.entity.TaskUser;

import java.util.List;
import java.util.Map;

/**
 * @author mac
 */
public interface TaskUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaskUser record);

    int insertSelective(TaskUser record);

    TaskUser selectByPrimaryKey(Integer id);

    /**
     * 查询列表by map
     * @param params
     * @return
     */
    List<TaskUser> selectByMap(Map<String, Object> params);

    int updateByPrimaryKeySelective(TaskUser record);

    int updateByPrimaryKey(TaskUser record);

    /**
     * 更新by map
     * @param params
     * @return
     */
    int updateByMap(Map<String, Object> params);
}