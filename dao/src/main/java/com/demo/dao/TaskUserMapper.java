package com.demo.dao;

import com.demo.entity.TaskUser;

public interface TaskUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaskUser record);

    int insertSelective(TaskUser record);

    TaskUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaskUser record);

    int updateByPrimaryKey(TaskUser record);
}