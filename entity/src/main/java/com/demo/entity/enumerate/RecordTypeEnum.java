package com.demo.entity.enumerate;

/**
 * @program: parent
 * @description:操作记录类型枚举类
 * @author: huangjie
 * @create: 2018-08-31 14:47
 **/

public enum RecordTypeEnum {
    /**
     * 用户加入
     */
    USER_ADD(0, "用户加入"),
    /**
     * 用户修改
     */
    USER_CHANGER(1, "用户修改"),
    /**
     * 用户退出
     */
    USER_DEL(2, "用户退出"),
    /**
     * 新建任务
     */
    TASK_CREATE(3,"新建任务"),
    /**
     * 转让任务
     */
    TASK_TRANSER(4,"转让任务"),
    /**
     * 完成任务
     */
    TASK_FINISH(5,"完成任务"),

    /**
     * 操作记录已删除
     * */
    RECORD_DEL(0,"已删除"),

    /**
     * 启用
     * */
    RECORD_ENABLE(1,"启用");

    private Integer code;
    private String msg;

    RecordTypeEnum(Integer code, String msg) {
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
