package com.demo.web.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.entity.Provinces;
import com.demo.entity.common.Result;
import com.demo.service.iservice.IUserService;
import com.demo.utils.common.ResultUtil;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @program: Spring-parent
 * @description:
 * @author: zouweidong
 * @create: 2018-08-17 16:43
 **/
@RestController
@ResponseBody
@RequestMapping("/test")
public class UserController {

    @Reference
    IUserService iUserService;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/getUser")
    public Result getUser()
    {
        return ResultUtil.success(iUserService.getUser());
    }

    @GetMapping("/getProvince")
    public Result getProvince()
    {
        Provinces provinces = iUserService.getProvince();
        logger.info("省市--->"+provinces.getProvince()+provinces.getCity());
        return ResultUtil.success(provinces);
    }

    @GetMapping("/getRedis")
    public Result getRedis()
    {
        return ResultUtil.success(iUserService.getRedis());
    }

    @GetMapping("/getPages")
    public Result getPage(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize)
    {
        logger.info("分页==========pageNum,pageSize"+pageNum+"  "+pageSize);
        PageInfo<Provinces> provincesPageInfo = iUserService.listProvince(pageNum,pageSize);
        return ResultUtil.success(provincesPageInfo);
    }
}
