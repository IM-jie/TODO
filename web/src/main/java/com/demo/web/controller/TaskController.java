package com.demo.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.entity.AdminUser;
import com.demo.entity.TaskInfo;
import com.demo.entity.common.Result;
import com.demo.entity.enumerate.TaskStatusEnum;
import com.demo.entity.param.TaskAddParam;
import com.demo.service.iservice.ITaskInfoSV;
import com.demo.utils.NumberComponent;
import com.demo.utils.common.EmptyUtil;
import com.demo.utils.common.GeneralException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * @program: parent
 * @description: 任务接口
 * @author: huangjie
 * @create: 2018-08-28 16:28
 **/

@RestController
@RequestMapping("/mytodo/task")
public class TaskController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @Reference
    private ITaskInfoSV iTaskInfoSV;

    @Autowired
    private NumberComponent numberComponent;

    /**
     * @apiDescription 任务获取接口
     * Author: huangjie
     * @api {get} /mytodo/task/{taskid} 任务获取接口
     * @apiName getTaskInfo
     * @apiGroup task
     * @apiVersion 0.0.1
     * @apiParam (请求参数说明){Long} id  id
     * @apiSuccess (返回结果参数说明){Integer} returnCode  返回状态码(0:成功,1:失败)
     * @apiSuccess (返回结果参数说明){String} returnMessage  返回信息
     * @apiSuccess (返回结果参数说明){JSONObject} bean  角色信息
     * @apiSuccess (角色信息字段说明){Long} id  id
     * @apiSuccess (角色信息字段说明){String} roleId  角色id
     * @apiSuccess (角色信息字段说明){String} roleName  角色名称
     * @apiSuccess (角色信息字段说明){Integer} status  状态（0-禁用 1-可用）
     * @apiSuccess (角色信息字段说明){Date} roleCreate  创建时间
     * @apiSuccess (角色信息字段说明){Date} roleModified  修改时间
     * @apiSuccess (角色信息字段说明){String} roleProvince  省份
     * @apiSuccess (角色信息字段说明){String} roleCity  城市
     * @apiSuccess (角色信息字段说明){String} roleCreator  创建人
     * @apiSuccess (角色信息字段说明){Integer} roleModifier  修改人
     * @apiSuccessExample json  返回样例
     * {
     * "code": 0,
     * "msg": "获取成功",
     * "data": {
     * "id": 1,
     * "taskId": "1",
     * "content": "新建",
     * "creator": "黄杰",
     * "finisher": "黄杰",
     * "worker": "黄杰",
     * "status": 1,
     * "privateStatus": 0,
     * "markStatus": 0,
     * "priority": 0,
     * "startTime": 1535523954000,
     * "endTime": 1535523958000,
     * "createTime": 1535523960000,
     * "finishTime": 1535523963000,
     * "commentCount": 3,
     * "creatorId": "1",
     * "finisherId": "1",
     * "workerId": "1"
     * }
     * }
     */
    @GetMapping("/{taskid}")
    public Result getTaskInfo(@RequestAttribute(name = "loginUser") AdminUser loginUser, @PathVariable(name = "taskid") String taskid) throws GeneralException {
        try {
            LOGGER.info("loginUser" + loginUser.getUsername());
            LOGGER.info("taskid-->" + taskid);
            if (EmptyUtil.isEmpty(taskid)) {
                return new Result(1, "taskid为空");
            }
            return new Result(0, "获取成功", iTaskInfoSV.getOneTaskInfo(taskid));
        } catch (Exception e) {
            LOGGER.info("异常" + e);
            if (e instanceof GeneralException) {
                return new Result(Integer.parseInt(((GeneralException) e).getErrorCode()), e.getMessage());
            }
            throw new GeneralException("系统错误");
        }
    }


    /**
     * @apiDescription 添加任务接口
     * Author: huangjie
     * @api {post} /mytodo/task 任务获取接口
     * @apiName createTask
     * @apiGroup task
     * @apiVersion 0.0.1
     */
    @PostMapping("")
    public Result createTask(@RequestAttribute(name = "loginUser") AdminUser adminUser, @RequestBody @Valid TaskAddParam taskAddParam) throws GeneralException {
        try {
            LOGGER.info("adminUser-->"+adminUser.toString());
            LOGGER.info("taskAddParam-->"+taskAddParam.toString());
            if (taskAddParam.getPrivateStatus() == 1 && !taskAddParam.getWorkerId().equals(adminUser.getUserId())) {
                return new Result(1, "私有TODO不能添加给别人");
            }
            TaskInfo taskInfo = new TaskInfo();
            taskInfo.setTaskId(numberComponent.getGuid());
            taskInfo.setCreator(adminUser.getUsername());
            taskInfo.setCreatorId(adminUser.getUserId());
            taskInfo.setContent(taskAddParam.getContent());
            taskInfo.setWorkerId(taskAddParam.getWorkerId());
            taskInfo.setWorker(taskAddParam.getWorker());
            taskInfo.setStartTime(taskAddParam.getStartTime());
            taskInfo.setEndTime(taskAddParam.getEndTime());
            taskInfo.setCreateTime(new Date());
            taskInfo.setPrivateStatus(taskAddParam.getPrivateStatus());
            taskInfo.setMarkStatus(taskAddParam.getMarkStatus());
            taskInfo.setStatus(TaskStatusEnum.STATUS_ON.getCode());
            LOGGER.info("taskinfo-->"+taskInfo,toString());
            if (!iTaskInfoSV.createTask(taskInfo)) {
                LOGGER.info("任务创建失败");
                return new Result(1, "任务创建失败");
            }
            //创建任务给别人
//            if (!taskAddParam.getWorkerId().equals(adminUser.getUserId())) {
//                //添加我的关注
//            }

            return new Result(0, "创建成功");
        } catch (Exception e) {
            LOGGER.info("异常" + e);
            if (e instanceof GeneralException) {
                return new Result(Integer.parseInt(((GeneralException) e).getErrorCode()), e.getMessage());
            }
            throw new GeneralException("系统错误");
        }
    }


}
