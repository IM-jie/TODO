package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class UserMessage implements Serializable {
    private Integer id;

    private String taskid;

    private String fromUserid;

    private String toUserid;

    private String message;

    private Date createTime;

    private Integer isread;

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

    public String getFromUserid() {
        return fromUserid;
    }

    public void setFromUserid(String fromUserid) {
        this.fromUserid = fromUserid == null ? null : fromUserid.trim();
    }

    public String getToUserid() {
        return toUserid;
    }

    public void setToUserid(String toUserid) {
        this.toUserid = toUserid == null ? null : toUserid.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsread() {
        return isread;
    }

    public void setIsread(Integer isread) {
        this.isread = isread;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", taskid=").append(taskid);
        sb.append(", fromUserid=").append(fromUserid);
        sb.append(", toUserid=").append(toUserid);
        sb.append(", message=").append(message);
        sb.append(", createTime=").append(createTime);
        sb.append(", isread=").append(isread);
        sb.append("]");
        return sb.toString();
    }
}