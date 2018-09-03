package com.demo.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.entity.AdminUser;
import com.demo.entity.common.PageResult;
import com.demo.entity.common.Result;
import com.demo.entity.ext.TaskRecordExt;
import com.demo.service.iservice.ITaskRecordSV;
import com.demo.utils.common.ResultUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: parent
 * @description: 操作记录接口类
 * @author: zouweidong
 * @create: 2018-09-03 16:38
 **/
@RestController
@RequestMapping(value = "mytodo/records")
public class TaskRecordController {

    @Reference
    private ITaskRecordSV iTaskRecordSV;

    @GetMapping(value = "")
    public PageResult listRecord(@RequestParam(name = "pageNum") Integer pageNum, @RequestParam(name = "pageSize") Integer pageSize) {
        return iTaskRecordSV.listRecord(pageNum, pageSize);
    }

    @GetMapping(value = "/taskid")
    public Result listRecordByTaskId(@RequestParam(name = "taskId") String taskId) {
        List<TaskRecordExt> taskRecordExtList = iTaskRecordSV.listRecordByTaskId(taskId);
        return ResultUtil.success(taskRecordExtList);
    }

    @DeleteMapping(value = "/{id}")
    public Result deleteRecord(@PathVariable(name = "id") Integer id) {
        if (0 != iTaskRecordSV.deleteRecord(id)) {
            return ResultUtil.success(1001, "删除成功");
        } else {
            return ResultUtil.error(1002, "删除失败");
        }
    }
}
