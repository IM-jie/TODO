package com.demo.dao;

import com.demo.entity.UserMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mac
 */
public interface UserMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserMessage record);

    int insertSelective(UserMessage record);

    UserMessage selectByPrimaryKey(Integer id);

    List<UserMessage>selectByMap(Map<String, Object> params);

    int updateByPrimaryKeySelective(UserMessage record);

    int updateByPrimaryKey(UserMessage record);

    int updateByMap(Map<String, Object> params);
}