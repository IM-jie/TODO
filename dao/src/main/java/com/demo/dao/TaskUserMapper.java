package com.demo.dao;

import com.demo.entity.TaskUser;

public interface TaskUserMapper {
    int deleteByPrimaryKey(String taskid);

    int insert(TaskUser record);

    int insertSelective(TaskUser record);

    TaskUser selectByPrimaryKey(String taskid);

    int updateByPrimaryKeySelective(TaskUser record);

    int updateByPrimaryKey(TaskUser record);
}