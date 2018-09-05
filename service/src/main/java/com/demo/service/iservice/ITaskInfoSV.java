package com.demo.service.iservice;

import com.demo.entity.TaskInfo;
import com.demo.utils.common.GeneralException;

import java.util.List;

/**
 * @program: parent
 * @description: 任务详情接口
 * @author: huangjie
 * @create: 2018-08-28 14:46
 **/

public interface ITaskInfoSV {
    /**
     * @param taskId
     * @return
     * @description；获取任务详细信息
     */
    TaskInfo getOneTaskInfo(String taskId) throws GeneralException;

    /**
     * @param taskInfo
     * @return
     * @description: 创建任务
     */
    boolean createTask(TaskInfo taskInfo) throws GeneralException;

    /**
     * @param taskId
     * @return
     * @description: 完成任务或者撤销完成任务
     */
    boolean finishTask(String userId, String taskId) throws GeneralException;

    /**
     * @param taskId
     * @param userId
     * @return
     * @description:转让任务
     */
    boolean transferTask(String taskId, String userId) throws GeneralException;

    /**
     * @param taskId
     * @param userId
     * @return
     * @description:关注任务、取消关注任务
     */
    boolean followTask(String taskId, String userId) throws GeneralException;

    /**
     * @param userId
     * @param taskId
     * @return
     * @description:星标任务、取消星标任务
     */
    boolean statTask(String userId, String taskId) throws GeneralException;

    /**
     * @param userId
     * @param taskId
     * @return
     * @description:私有任务、 取消私有任务
     */
    boolean privateTask(String userId, String taskId) throws GeneralException;

    /**
     * @return
     * @description:完成所有未完成任务
     */
    boolean finishAllTask(String userid) throws GeneralException;

    /**
     * @return
     * @description:删除所有完成任务
     */
    boolean deleteAllTask(String userid) throws GeneralException;


    /**
     * 获取我的任务接口
     * @param userId
     * @return
     * @throws GeneralException
     */
    List<TaskInfo> taskList(String userId) throws GeneralException;

    /**
     * 获取我的关注列表
     * @param userId
     * @return
     * @throws GeneralException
     */
    List<TaskInfo> attentionTaskList(String userId) throws GeneralException;
}
