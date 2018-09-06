package com.demo.dao;

import com.demo.entity.TaskInfo;

import java.util.List;
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

    List<TaskInfo> selectByMap(Map<String, Object> params);

    int updateMany(Map<String, Object> params);

    List<TaskInfo> selectAttentionTask(String userId);
}