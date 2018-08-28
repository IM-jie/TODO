package com.demo.dao;

import com.demo.entity.TaskComment;

/**
 * @author mac
 */
public interface TaskCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaskComment record);

    int insertSelective(TaskComment record);

    TaskComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaskComment record);

    int updateByPrimaryKey(TaskComment record);
}