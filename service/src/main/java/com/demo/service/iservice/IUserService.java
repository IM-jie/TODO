package com.demo.service.iservice;

import com.demo.entity.Provinces;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @program: Spring-parent
 * @description:
 * @author: zouweidong
 * @create: 2018-08-17 16:44
 **/
public interface IUserService {

     String getUser();

     Provinces getProvince();

     String getRedis();

     PageInfo<Provinces> listProvince(Integer pageNum, Integer pageSize);
}
