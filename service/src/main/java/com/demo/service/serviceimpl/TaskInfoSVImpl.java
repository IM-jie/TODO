package com.demo.service.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.demo.dao.TaskInfoMapper;
import com.demo.dao.TaskRecordMapper;
import com.demo.dao.TaskUserMapper;
import com.demo.entity.TaskInfo;
import com.demo.entity.TaskRecord;
import com.demo.entity.TaskUser;
import com.demo.entity.enumerate.RecordTypeEnum;
import com.demo.service.iservice.ITaskInfoSV;
import com.demo.utils.NumberComponent;
import com.demo.utils.common.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
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

    @Autowired
    private TaskRecordMapper taskRecordMapper;

    @Autowired
    private NumberComponent numberComponent;

    @Autowired
    private TaskUserMapper taskUserMapper;

    /**
     * @param taskId
     * @return
     * @description；获取任务详细信息
     */
    @Override
    public TaskInfo getOneTaskInfo(String taskId) throws GeneralException {
        Map<String, Object> param = new HashMap<>();
        param.put("eqTaskid", taskId);
        return taskInfoMapper.selectByMap(param);
    }

    /**
     * @param taskInfo
     * @return
     * @description: 创建任务
     */
    @Override
    public boolean createTask(TaskInfo taskInfo) throws GeneralException {
        int result = taskInfoMapper.insert(taskInfo);
        if (result == 1) {
            //添加操作记录
            TaskRecord taskRecord = new TaskRecord();
            taskRecord.setRecordId(numberComponent.getGuid());
            taskRecord.setTaskId(taskInfo.getTaskId());
            taskRecord.setOperateTime(new Date());
            if (!taskInfo.getCreatorId().equals(taskInfo.getWorkerId())) {
                taskRecord.setOperateType(RecordTypeEnum.TASK_TRANSER.getCode());
                taskRecord.setOperate("将TODO【" + taskInfo.getContent() + "】转让给了" + taskInfo.getWorker());
            } else {
                taskRecord.setOperateType(RecordTypeEnum.TASK_CREATE.getCode());
                taskRecord.setOperate("添加了TODO【" + taskInfo.getContent() + "】");
            }
            taskRecord.setOperator(taskInfo.getCreatorId());
            taskRecord.setStatus(RecordTypeEnum.RECORD_ENABLE.getCode());
            if (this.insertTaskRecord(taskRecord)) {
                if (!taskInfo.getCreatorId().equals(taskInfo.getWorkerId())) {
                    TaskUser taskUser = new TaskUser();
                    taskUser.setTaskId(taskInfo.getTaskId());
                    taskUser.setFollowTime(new Date());
                    taskUser.setUserId(taskInfo.getCreatorId());
                    if(!this.payAttentionTask(taskUser)){
                        throw new GeneralException("1","添加关注失败");
                    }
                }
                return true;
            }
            throw new GeneralException("1","添加操作记录失败");
        }
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

    /**
     * 添加操作记录
     *
     * @param taskRecord
     * @return
     */
    private boolean insertTaskRecord(TaskRecord taskRecord) {
        int result = taskRecordMapper.insert(taskRecord);
        if (result == 1) {
            return true;
        }
        return false;
    }

    /**
     * 添加关注
     * @param taskUser
     * @return
     */
    private boolean payAttentionTask(TaskUser taskUser) {
        int result = taskUserMapper.insert(taskUser);
        if (result == 1){
            return true;
        }
        return false;
    }
}
