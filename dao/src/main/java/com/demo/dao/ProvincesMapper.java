package com.demo.dao;

import com.demo.entity.Provinces;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProvincesMapper {
    int deleteByPrimaryKey(String id);

    int insert(Provinces record);

    int insertSelective(Provinces record);

    Provinces selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Provinces record);

    int updateByPrimaryKey(Provinces record);

    List<Provinces>selectAll();
}