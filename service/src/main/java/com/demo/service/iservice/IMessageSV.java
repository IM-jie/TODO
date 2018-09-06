package com.demo.service.iservice;

import com.demo.entity.AdminUser;
import com.demo.entity.UserMessage;
import com.demo.entity.common.PageResult;
import com.github.pagehelper.PageInfo;

/**
 * @program: parent
 * @description: 收件箱服务接口类
 * @author: zouweidong
 * @create: 2018-09-03 15:33
 **/
public interface IMessageSV {

    /**
     * 获取收件箱列表
     * @param pageNum
     * @param pageSize
     * @param loginUser
     * @return
     */
    PageResult<UserMessage> listMessage(Integer pageNum, Integer pageSize, AdminUser loginUser);
}
