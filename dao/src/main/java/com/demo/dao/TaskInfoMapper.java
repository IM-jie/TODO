package com.demo.dao;

import com.demo.entity.TaskInfo;

import java.util.Map;

/**
 * @author mac
 */
public interface TaskInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaskInfo record);

    int insertSelective(TaskInfo record);

    TaskInfo selectByPrimaryKey(Integer id);

    TaskInfo selectByTaskId(String taskId);

    int updateByPrimaryKeySelective(TaskInfo record);

    int updateByPrimaryKey(TaskInfo record);

    TaskInfo selectByMap(Map<String, Object> params);

    int updateMany(Map<String, Object> params);
}