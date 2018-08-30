package com.demo.dao;

import com.demo.entity.TaskComment;

import java.util.List;
import java.util.Map;

/**
 * @author mac
 */
public interface TaskCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaskComment record);

    int insertSelective(TaskComment record);

    TaskComment selectByPrimaryKey(Integer id);

    List<TaskComment> selectByMap(Map<String, Object> params);

    int updateByPrimaryKeySelective(TaskComment record);

    int updateByPrimaryKey(TaskComment record);

    int updateByMap(Map<String, Object> params);

}