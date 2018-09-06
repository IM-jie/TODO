package com.demo.service.serviceimpl;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.demo.dao.TaskCommentMapper;
import com.demo.entity.TaskComment;
import com.demo.service.iservice.ICommentInfoSV;
import com.demo.utils.NumberComponent;
import com.demo.utils.RandomStringUtil;
import com.demo.utils.common.GeneralException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: parent
 * @description: 任务评论接口实现类
 * @author: zhangchen
 * @create: 2018-08-31 15:27
 **/
@Service
public class CommentInfoSVImpl implements ICommentInfoSV {

    @Autowired
    private TaskCommentMapper taskCommentMapper;

    @Autowired
    private NumberComponent numberComponent;

    Logger logger = LoggerFactory.getLogger(CommentInfoSVImpl.class);

    /**
     * @param taskId
     * @return
     * @description；获取任务评论列表
     */
    @Override
    public List<TaskComment> getTaskCommentList(String taskId) {
        Map<String, Object> params = new HashMap<>();
        params.put("eqTaskId", taskId);
        logger.info("任务taskid:" + taskId);
        return taskCommentMapper.selectByMap(params);

    }

    /**
     * @param taskId,comment,commentator
     * @return
     * @description；添加评论
     */
    @Override
    public boolean addTaskComment(String taskId, String comment, String commentator) {
        logger.info("用户名称：loginUser:" + commentator);
        TaskComment taskComment = new TaskComment();
        taskComment.setCommentId(numberComponent.getGuid());
        taskComment.setTaskId(taskId);
        taskComment.setCommentator(commentator);
        taskComment.setComment(comment);
        taskComment.setCommentTime(new Date());
        taskComment.setStatus(1);
        if (taskCommentMapper.insert(taskComment) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param id,userName
     * @return
     * @description；删除评论
     */
    @Override
    public int delTaskComment(Integer id, String userName) {
        TaskComment taskComment=new TaskComment();
        Map<String, Object> params = new HashMap<>();
        params.put("eqId", id);
        taskComment=taskCommentMapper.selectByPrimaryKey(id);
        if(taskComment.getCommentator().equals(userName)) {
            Map<String, Object> param = new HashMap<>();
            param.put("eqId",id);
            param.put("status",0);
            return taskCommentMapper.updateByMap(param);
        }
        else
            return -1;
        //    return false;
    }

}
