package com.demo.service.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.demo.dao.TaskInfoMapper;
import com.demo.entity.TaskInfo;
import com.demo.service.iservice.ITaskInfoSV;
import com.demo.utils.common.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: parent
 * @description: 任务接口实现类
 * @author: huangjie
 * @create: 2018-08-28 15:21
 **/

@Service
public class TaskInfoSVImpl implements ITaskInfoSV {

    @Autowired
    private TaskInfoMapper taskInfoMapper;

    /**
     * @param taskId
     * @return
     * @description；获取任务详细信息
     */
    @Override
    public TaskInfo getOneTaskInfo(String taskId) throws GeneralException {
        Map<String, String> param=new HashMap<>();
        param.put("taskid",taskId);
        return taskInfoMapper.selectByMap(param);
    }

    /**
     * @param taskInfo
     * @return
     * @description: 创建任务
     */
    @Override
    public boolean createTask(TaskInfo taskInfo) {
        return false;
    }

    /**
     * @param taskId
     * @return
     * @description: 完成任务或者撤销完成任务
     */
    @Override
    public boolean finishTask(String taskId) {
        return false;
    }

    /**
     * @param taskId
     * @param userId
     * @return
     * @description:转让任务
     */
    @Override
    public boolean transferTask(String taskId, String userId) {
        return false;
    }

    /**
     * @param taskId
     * @param userId
     * @return
     * @description:关注任务、取消关注任务
     */
    @Override
    public boolean followTask(String taskId, String userId) {
        return false;
    }

    /**
     * @param taskId
     * @return
     * @description:星标任务、取消星标任务
     */
    @Override
    public boolean statTask(String taskId) {
        return false;
    }

    /**
     * @param taskId
     * @return
     * @description:私有任务、 取消私有任务
     */
    @Override
    public boolean privateTask(String taskId) {
        return false;
    }

    /**
     * @return
     * @description:完成所有未完成任务
     */
    @Override
    public boolean finishAllTask() {
        return false;
    }

    /**
     * @return
     * @description:删除所有完成任务
     */
    @Override
    public boolean deleteAllTask() {
        return false;
    }
}
