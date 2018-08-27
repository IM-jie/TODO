package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class TaskInfo implements Serializable {
    private Integer id;

    private String taskId;

    private String content;

    private String creator;

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

    private String creatorId;

    private String finisherId;

    private String workerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
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

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    public String getFinisherId() {
        return finisherId;
    }

    public void setFinisherId(String finisherId) {
        this.finisherId = finisherId == null ? null : finisherId.trim();
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId == null ? null : workerId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", taskId=").append(taskId);
        sb.append(", content=").append(content);
        sb.append(", creator=").append(creator);
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
        sb.append(", creatorId=").append(creatorId);
        sb.append(", finisherId=").append(finisherId);
        sb.append(", workerId=").append(workerId);
        sb.append("]");
        return sb.toString();
    }
}