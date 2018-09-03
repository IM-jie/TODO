package com.demo.entity.ext;

import com.demo.entity.AdminUser;
import com.demo.entity.TaskInfo;
import com.demo.entity.TaskRecord;

/**
 * @program: parent
 * @description: 操作记录扩展类
 * @author: zouweidong
 * @create: 2018-09-03 16:59
 **/
public class TaskRecordExt extends TaskRecord {

    private AdminUser user;

    private TaskInfo taskInfo;

    public AdminUser getUser() {
        return user;
    }

    public void setUser(AdminUser user) {
        this.user = user;
    }

    public TaskInfo getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(TaskInfo taskInfo) {
        this.taskInfo = taskInfo;
    }
}
