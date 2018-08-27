package com.demo.dao;

import com.demo.entity.TaskRecord;

public interface TaskRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaskRecord record);

    int insertSelective(TaskRecord record);

    TaskRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaskRecord record);

    int updateByPrimaryKey(TaskRecord record);
}