package com.demo.service.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.demo.dao.AdminUserMapper;
import com.demo.dao.TaskInfoMapper;
import com.demo.dao.TaskRecordMapper;
import com.demo.dao.TaskUserMapper;
import com.demo.entity.AdminUser;
import com.demo.entity.TaskInfo;
import com.demo.entity.TaskRecord;
import com.demo.entity.TaskUser;
import com.demo.entity.enumerate.RecordTypeEnum;
import com.demo.entity.enumerate.TaskStatusEnum;
import com.demo.service.iservice.IAdminUserSV;
import com.demo.service.iservice.ITaskInfoSV;
import com.demo.utils.NumberComponent;
import com.demo.utils.common.EmptyUtil;
import com.demo.utils.common.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private AdminUserMapper adminUserMapper;

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
            taskRecord.setOperator(taskInfo.getCreator());
            taskRecord.setOperatorId(taskInfo.getCreatorId());
            taskRecord.setStatus(RecordTypeEnum.RECORD_ENABLE.getCode());
            if (this.insertTaskRecord(taskRecord)) {
                if (!taskInfo.getCreatorId().equals(taskInfo.getWorkerId())) {
                    TaskUser taskUser = new TaskUser();
                    taskUser.setTaskId(taskInfo.getTaskId());
                    taskUser.setFollowTime(new Date());
                    taskUser.setUserId(taskInfo.getCreatorId());
                    if (!this.payAttentionTask(taskUser)) {
                        throw new GeneralException("1", "添加关注失败");
                    }
                }
                return true;
            }
            throw new GeneralException("1", "添加操作记录失败");
        }
        return false;
    }

    /**
     * @param taskId
     * @return
     * @description: 完成任务或者撤销完成任务
     */
    @Override
    public boolean finishTask(String userId, String taskId) throws GeneralException {
        Map<String, Object> params = new HashMap<>();
        params.put("eqTaskid", taskId);
        TaskInfo taskInfo = taskInfoMapper.selectByMap(params);
        if (EmptyUtil.isNotEmpty(taskInfo)) {
            throw new GeneralException("1", "任务不存在");
        }
        if (!taskInfo.getWorkerId().equals(userId)){
            throw new GeneralException("1", "任务负责人出错");
        }
        if (EmptyUtil.isNotEmpty(taskInfo.getWorker())) {
            taskInfo.setFinisher(null);
            taskInfo.setFinisherId(null);
            if (taskInfoMapper.updateByPrimaryKey(taskInfo) == 1) {
                return true;
            }
            throw new GeneralException("1", "撤销完成任务失败");
        }
        taskInfo.setFinisher(taskInfo.getWorker());
        taskInfo.setFinisherId(taskInfo.getWorkerId());
        if (taskInfoMapper.updateByPrimaryKey(taskInfo) == 1) {
            TaskRecord taskRecord = new TaskRecord();
            taskRecord.setRecordId(numberComponent.getGuid());
            taskRecord.setTaskId(taskInfo.getTaskId());
            taskRecord.setOperateTime(new Date());
            taskRecord.setOperateType(RecordTypeEnum.TASK_FINISH.getCode());
            taskRecord.setOperate("完成了TODO【" + taskInfo.getContent() + "】");
            taskRecord.setOperator(taskInfo.getWorker());
            taskRecord.setOperatorId(taskInfo.getWorkerId());
            if (!this.insertTaskRecord(taskRecord)) {
                throw new GeneralException("1", "添加记录失败");
            }
            return true;
        }


        return false;
    }

    /**
     * @param taskId
     * @param userId
     * @return
     * @description:转让任务
     */
    @Override
    public boolean transferTask(String taskId, String userId) throws GeneralException {
        Map<String, Object> params = new HashMap<>();
        params.put("eqTaskid", taskId);
        TaskInfo taskInfo = taskInfoMapper.selectByMap(params);
        if (EmptyUtil.isNotEmpty(taskInfo)) {
            throw new GeneralException("1", "任务不存在");
        }
        if (taskInfo.getPrivateStatus() == 1) {
            throw new GeneralException("1", "私有任务禁止转让");
        }
        String userName = this.getUserName(userId);
        TaskRecord taskRecord = new TaskRecord();
        taskRecord.setRecordId(numberComponent.getGuid());
        taskRecord.setTaskId(taskInfo.getTaskId());
        taskRecord.setOperateTime(new Date());
        taskRecord.setOperateType(RecordTypeEnum.TASK_TRANSER.getCode());
        taskRecord.setOperate("将TODO【" + taskInfo.getContent() + "】转让给了" + userName);
        taskRecord.setOperator(taskInfo.getWorker());
        taskRecord.setOperatorId(taskInfo.getWorkerId());
        if (!this.insertTaskRecord(taskRecord)) {
            throw new GeneralException("1", "添加记录失败");
        }
        taskInfo.setWorkerId(userId);
        taskInfo.setWorker(userName);
        taskInfo.setMarkStatus(TaskStatusEnum.MARK_FALSE.getCode());
        if (taskInfoMapper.updateByPrimaryKey(taskInfo) == 1) {
            return true;
        }
        return false;
    }

    /**
     * @param taskId
     * @param userId
     * @return
     * @description:关注任务、取消关注任务
     */
    @Override
    public boolean followTask(String taskId, String userId) throws GeneralException {
        Map<String, Object> param = new HashMap<>();
        param.put("eqTaskid", taskId);
        TaskInfo taskInfo = taskInfoMapper.selectByMap(param);
        if (EmptyUtil.isEmpty(taskInfo)) {
            throw new GeneralException("0", "任务不存在");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("eqTaskId", taskId);
        params.put("eqUserId", userId);
        TaskUser taskUser = (TaskUser) taskUserMapper.selectByMap(params);
        if (EmptyUtil.isNotEmpty(taskUser)) {
            if (taskUserMapper.deleteByPrimaryKey(taskUser.getId()) == 1) {
                return true;
            }
            throw new GeneralException("0", "取消关注失败");
        }
        taskUser.setTaskId(taskId);
        taskUser.setUserId(userId);
        taskUser.setFollowTime(new Date());
        taskUserMapper.insert(taskUser);
        if (taskUserMapper.insert(taskUser) == 1) {
            return true;
        }
        throw new GeneralException("0", "关注失败");
    }

    /**
     * @param taskId
     * @return
     * @description:星标任务、取消星标任务
     */
    @Override
    public boolean statTask(String userId, String taskId) throws GeneralException {
        Map<String, Object> param = new HashMap<>();
        param.put("eqTaskid", taskId);
        TaskInfo taskInfo = taskInfoMapper.selectByMap(param);
        if (EmptyUtil.isEmpty(taskInfo)) {
            throw new GeneralException("0", "任务不存在");
        }
        if (!taskInfo.getWorkerId().equals(userId)){
            throw new GeneralException("1", "任务负责人出错");
        }
        if (taskInfo.getMarkStatus().equals(TaskStatusEnum.MARK_FALSE.getCode())) {
            taskInfo.setMarkStatus(TaskStatusEnum.MARK_TRUE.getCode());
        } else {
            taskInfo.setMarkStatus(TaskStatusEnum.MARK_FALSE.getCode());
        }
        if (taskInfoMapper.updateByPrimaryKey(taskInfo) == 1) {
            return true;
        }
        return false;
    }

    /**
     * @param taskId
     * @return
     * @description:私有任务、 取消私有任务
     */
    @Override
    public boolean privateTask(String userId, String taskId) throws GeneralException {
        Map<String, Object> param = new HashMap<>();
        param.put("eqTaskid", taskId);
        TaskInfo taskInfo = taskInfoMapper.selectByMap(param);
        if (EmptyUtil.isEmpty(taskInfo)) {
            throw new GeneralException("0", "任务不存在");
        }
        if (!taskInfo.getWorkerId().equals(userId)){
            throw new GeneralException("1", "任务负责人出错");
        }
        if (taskInfo.getMarkStatus().equals(TaskStatusEnum.PRIVATE_FALSE.getCode())) {
            taskInfo.setMarkStatus(TaskStatusEnum.PRIVATE_TRUE.getCode());
        } else {
            taskInfo.setMarkStatus(TaskStatusEnum.PRIVATE_FALSE.getCode());
        }
        if (taskInfoMapper.updateByPrimaryKey(taskInfo) == 1) {
            return true;
        }
        return false;
    }

    /**
     * @return
     * @description:完成所有未完成任务
     */
    @Override
    public boolean finishAllTask(String userid) throws GeneralException {
        String userName = this.getUserName(userid);
        if (EmptyUtil.isEmpty(userName)) {
            throw new GeneralException("1", "userId出错");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("finisher", userName);
        params.put("status", TaskStatusEnum.STATUS_FINISHED.getCode());
        params.put("workerId", userid);
        if (taskInfoMapper.updateMany(params) != 0) {
            return true;
        }
        return false;
    }

    /**
     * @return
     * @description:删除所有完成任务
     */
    @Override
    public boolean deleteAllTask(String userid) throws GeneralException {
        String userName = this.getUserName(userid);
        if (EmptyUtil.isEmpty(userName)) {
            throw new GeneralException("1", "userId出错");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("finisher", userName);
        params.put("status", TaskStatusEnum.STATUS_DEL.getCode());
        params.put("workerId", userid);
        if (taskInfoMapper.updateMany(params) != 0) {
            return true;
        }
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
     *
     * @param taskUser
     * @return
     */
    private boolean payAttentionTask(TaskUser taskUser) {
        int result = taskUserMapper.insert(taskUser);
        if (result == 1) {
            return true;
        }
        return false;
    }


    /**
     * 根据id获取用户姓名
     *
     * @param id
     * @return
     */
    private String getUserName(String id) {
        Map<String, Object> param = new HashMap<>();
        param.put("eqTaskId", id);
        AdminUser adminUser = adminUserMapper.selectByMap(param).get(0);
        if (EmptyUtil.isNotEmpty(adminUser)) {
            return adminUser.getUsername();
        }
        return null;
    }
}
