package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class TaskInfo implements Serializable {
    private Integer id;

    private String taskid;

    private String content;

    private String creater;

    private String finisher;

    private String worker;

    private Integer status;

    private Integer privateStatus;

    private Integer markStatus;

    private Integer priority;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private Date finishTime;

    private Integer commentCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid == null ? null : taskid.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public String getFinisher() {
        return finisher;
    }

    public void setFinisher(String finisher) {
        this.finisher = finisher == null ? null : finisher.trim();
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker == null ? null : worker.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", taskid=").append(taskid);
        sb.append(", content=").append(content);
        sb.append(", creater=").append(creater);
        sb.append(", finisher=").append(finisher);
        sb.append(", worker=").append(worker);
        sb.append(", status=").append(status);
        sb.append(", privateStatus=").append(privateStatus);
        sb.append(", markStatus=").append(markStatus);
        sb.append(", priority=").append(priority);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", finishTime=").append(finishTime);
        sb.append(", commentCount=").append(commentCount);
        sb.append("]");
        return sb.toString();
    }
}