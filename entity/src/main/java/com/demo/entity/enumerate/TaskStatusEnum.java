package com.demo.entity.enumerate;

/**
 * @program: parent
 * @description: 任务状态枚举类
 * @author: huangjie
 * @create: 2018-08-31 11:48
 **/

public enum  TaskStatusEnum {
    /**
     * 任务已删除
     */
    STATUS_DEL(0, "任务已删除"),
    /**
     * 任务进行中
     */
    STATUS_ON(1, "任务进行中"),
    /**
     * 任务已完成
     */
    STATUS_FINISHED(2, "任务已完成");

    private Integer code;
    private String msg;

    TaskStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
