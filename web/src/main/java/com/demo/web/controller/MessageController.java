package com.demo.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.entity.AdminUser;
import com.demo.entity.common.PageResult;
import com.demo.service.iservice.IMessageSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @program: parent
 * @description: 收件箱接口
 * @author: zouweidong
 * @create: 2018-09-03 15:31
 **/
@RestController
@RequestMapping(value = "/mytodo/messages")
public class MessageController {

    Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Reference
    IMessageSV iMessageSV;

    @GetMapping(value = "")
    public PageResult listMessage(@RequestParam(name = "pageNum")Integer pageNum,@RequestParam(name = "pageSize")Integer pageSize,@RequestAttribute(name = "loginUser")AdminUser loginUser)
    {
        logger.info("分页收件箱信息========pageNum,pageSize"+pageNum+"  "+pageSize);
        return iMessageSV.listMessage(pageNum,pageSize,loginUser);
    }
}
