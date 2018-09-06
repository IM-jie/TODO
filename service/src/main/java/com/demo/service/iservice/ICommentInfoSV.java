package com.demo.service.iservice;

import com.demo.utils.common.GeneralException;
import com.demo.entity.TaskComment;

import java.util.List;

/**
 * @program: parent
 * @description: 任务评论接口类
 * @author: zhangchen
 * @create: 2018-8-31 10:57
 */

public interface ICommentInfoSV {
    /**
     * @param taskId
     * @return
     * @description；获取任务评论列表
     */
    List<TaskComment> getTaskCommentList(String taskId);

    /**
     * @param taskId
     * @return
     * @description；添加评论
     */
    boolean addTaskComment(String taskId,String comment,String commentator);

    /**
     * @param id,userName
     * @return
     * @description；删除评论
     */
   int delTaskComment(Integer id,String userName);


}

