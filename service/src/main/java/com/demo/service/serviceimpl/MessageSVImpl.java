package com.demo.service.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.demo.dao.UserMessageMapper;
import com.demo.entity.AdminUser;
import com.demo.entity.UserMessage;
import com.demo.entity.common.PageResult;
import com.demo.service.iservice.IMessageSV;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: parent
 * @description: 收件箱服务实现类
 * @author: zouweidong
 * @create: 2018-09-03 15:34
 **/
@Service
public class MessageSVImpl implements IMessageSV {

    @Autowired
    private UserMessageMapper userMessageMapper;

    @Override
    public PageResult<UserMessage> listMessage(Integer pageNum, Integer pageSize, AdminUser loginUser)
    {
        List<UserMessage> messageList;
        Map<String,Object>params = new HashMap<>();
        params.put("eqToUserId",loginUser.getUserId());
        PageHelper.startPage(pageNum,pageSize);
        List<UserMessage> userMessageList = userMessageMapper.selectByMap(params);
        return new PageResult<>(userMessageList);
    }
}
