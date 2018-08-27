package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class TaskRecord implements Serializable {
    private Integer id;

    private String recordid;

    private String taskid;

    private Integer operateType;

    private String operate;

    private String operator;

    private Date operateTime;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid == null ? null : recordid.trim();
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid == null ? null : taskid.trim();
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate == null ? null : operate.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", recordid=").append(recordid);
        sb.append(", taskid=").append(taskid);
        sb.append(", operateType=").append(operateType);
        sb.append(", operate=").append(operate);
        sb.append(", operator=").append(operator);
        sb.append(", operateTime=").append(operateTime);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}