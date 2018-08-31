package com.demo.entity.param;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: parent
 * @description: 添加任务校验类
 * @author: huangjie
 * @create: 2018-08-30 18:20
 **/

public class TaskAddParam implements Serializable {

    @NotEmpty(message = "内容不能为空")
    private String content;

    @NotEmpty(message = "负责人不能为空")
    private String worker;

    private Integer privateStatus;

    private Integer markStatus;

    private Date startTime;

    private Date endTime;

    @NotEmpty(message = "负责人Id不能为空")
    private String workerId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public Integer getPrivateStatus() {
        return privateStatus;
    }

    public void setPrivateStatus(Integer privateStatus) {
        this.privateStatus = privateStatus;
    }

    public Integer getMarkStatus() {
        return markStatus;
    }

    public void setMarkStatus(Integer markStatus) {
        this.markStatus = markStatus;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }
}
