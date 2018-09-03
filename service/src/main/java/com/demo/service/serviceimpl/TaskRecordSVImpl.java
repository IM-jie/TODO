package com.demo.service.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.demo.dao.TaskRecordMapper;
import com.demo.entity.AdminUser;
import com.demo.entity.TaskRecord;
import com.demo.entity.UserMessage;
import com.demo.entity.common.PageResult;
import com.demo.entity.ext.TaskRecordExt;
import com.demo.service.iservice.ITaskRecordSV;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: parent
 * @description: 操作记录服务实现类
 * @author: zouweidong
 * @create: 2018-09-03 16:40
 **/
@Service
public class TaskRecordSVImpl implements ITaskRecordSV {

    @Autowired
    private TaskRecordMapper taskRecordMapper;

    @Override
    public PageResult<TaskRecordExt> listRecord(Integer pageNum, Integer pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("neqStatus", 0);
        PageHelper.startPage(pageNum, pageSize);
        List<TaskRecordExt> taskRecordExtList = taskRecordMapper.selectExtByMap(params);
        return new PageResult<>(taskRecordExtList);
    }

    @Override
    public List<TaskRecordExt> listRecordByTaskId(String taskId) {
        Map<String,Object> params = new HashMap<>();
        params.put("eqTaskId",taskId);
        params.put("neqStatus", 0);
        return taskRecordMapper.selectExtByMap(params);
    }

    @Override
    public int deleteRecord(Integer id) {
        TaskRecord taskRecord = taskRecordMapper.selectByPrimaryKey(id);
        taskRecord.setStatus(0);
        return taskRecordMapper.updateByPrimaryKeySelective(taskRecord);
    }
}
