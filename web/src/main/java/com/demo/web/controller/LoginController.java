package com.demo.web.controller;

import com.demo.entity.common.Result;
import com.demo.utils.common.ResultUtil;
import org.springframework.web.bind.annotation.*;

/**
 * @program: parent
 * @description: 登录接口
 * @author: zouweidong
 * @create: 2018-08-28 09:13
 **/
@RestController
@RequestMapping("/todo")
public class LoginController {


    @GetMapping("/login")
    public Result login(@RequestParam(value = "mail")String mail,@RequestParam(value = "password")String password)
    {

        return ResultUtil.success("登录成功");
    }
}
