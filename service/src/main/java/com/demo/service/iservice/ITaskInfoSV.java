package com.demo.service.iservice;

import com.demo.entity.TaskInfo;
import com.demo.utils.common.GeneralException;

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
    boolean finishTask(String taskId) throws GeneralException;

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
     * @param taskId
     * @return
     * @description:星标任务、取消星标任务
     */
    boolean statTask(String taskId) throws GeneralException;

    /**
     * @param taskId
     * @return
     * @description:私有任务、 取消私有任务
     */
    boolean privateTask(String taskId) throws GeneralException;

    /**
     * @return
     * @description:完成所有未完成任务
     */
    boolean finishAllTask() throws GeneralException;

    /**
     * @return
     * @description:删除所有完成任务
     */
    boolean deleteAllTask() throws GeneralException;

}
