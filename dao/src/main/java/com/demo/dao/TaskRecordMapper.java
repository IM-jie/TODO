package com.demo.dao;

import com.demo.entity.TaskRecord;
import com.demo.entity.ext.TaskRecordExt;

import java.util.List;
import java.util.Map;

/**
 * @author mac
 */
public interface TaskRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaskRecord record);

    int insertSelective(TaskRecord record);

    TaskRecord selectByPrimaryKey(Integer id);

    List<TaskRecordExt> selectExtByMap(Map<String, Object> params);

    List<TaskRecord> selectByMap(Map<String, Object> params);

    int updateByPrimaryKeySelective(TaskRecord record);

    int updateByPrimaryKey(TaskRecord record);

    int updateByMap(Map<String, Object> params);
}