package com.demo.service.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.demo.dao.ProvincesMapper;
import com.demo.entity.Provinces;
import com.demo.service.iservice.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * @program: Spring-parent
 * @description:
 * @author: zouweidong
 * @create: 2018-08-17 16:48
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private ProvincesMapper provincesMapper;

    @Autowired
    private JedisPool jedisPool;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public String getUser()
    {
        return "h 哥哥";
    }

    @Override
    public Provinces getProvince()
    {
        return provincesMapper.selectByPrimaryKey("1");
    }

    @Override
    public String getRedis()
    {
        Jedis jedis = jedisPool.getResource();
        List<Provinces> provinceList = provincesMapper.selectAll();
        String provinceJson = JSON.toJSONString(provinceList);
        jedis.setex("province",60,provinceJson);
        return JSONArray.parseArray(jedis.get("province"),Provinces.class).toString();
    }

    @Override
    public PageInfo<Provinces> listProvince(Integer pageNum,Integer pageSize)
    {
        List<Provinces> provincesList;
        PageHelper.startPage(pageNum,pageSize);
        provincesList = provincesMapper.selectAll();
        PageInfo<Provinces> provincesPageInfo = new PageInfo<>(provincesList);
        return provincesPageInfo;
    }
}
