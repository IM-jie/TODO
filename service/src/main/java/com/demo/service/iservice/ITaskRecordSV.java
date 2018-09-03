package com.demo.service.iservice;

import com.demo.entity.common.PageResult;
import com.demo.entity.ext.TaskRecordExt;

import java.util.List;

/**
 * @program: parent
 * @description: 操作记录服务接口类
 * @author: zouweidong
 * @create: 2018-09-03 16:40
 **/
public interface ITaskRecordSV {

    /**
     * 获取操作记录列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult<TaskRecordExt> listRecord(Integer pageNum, Integer pageSize);

    /**
     * 根据任务ID获取操作记录
     * @param taskId
     * @return
     */
    List<TaskRecordExt> listRecordByTaskId(String taskId);

    /**
     * 删除操作记录
     * @param id
     * @return
     */
    int deleteRecord(Integer id);
}
