package com.demo.web.controller;

import com.demo.entity.TaskInfo;
import com.demo.entity.common.Result;
import com.demo.service.iservice.ITaskInfoSV;
import com.demo.utils.common.EmptyUtil;
import com.demo.utils.common.GeneralException;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: parent
 * @description: 任务接口
 * @author: huangjie
 * @create: 2018-08-28 16:28
 **/

@RestController
@RequestMapping("/todo/task")
public class TaskController {

    private Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @Reference
    private ITaskInfoSV iTaskInfoSV;

    /**
     * @apiDescription 任务获取接口
     * Author: huangjie
     * @api {get} /todo/task/{taskid} 任务获取接口
     * @apiName getRoleMessage
     * @apiGroup adminroles
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
     * "returnCode": "0",
     * "returnMessage": "查询成功",
     * "bean":{
     * "roleModified": "2018-08-01 18:13:30",
     * "roleCity": "郑州",
     * "roleId": "2",
     * "roleModifier": "huangjie",
     * "roleName": "开发",
     * "roleProvince": "河南",
     * "roleCreator": "huangjie",
     * "id": 1,
     * "roleCreate": "2018-08-01 18:12:30",
     * "status": 1
     * }
     * }
     */
    @GetMapping("/{taskid}")
    public Result getTaskInfo(@PathVariable(name = "taskid") String taskid) throws GeneralException {
        try{
            if (EmptyUtil.isEmpty(taskid)){
                return new Result(1,"taskid为空");
            }
            return new Result(0,"获取成功",iTaskInfoSV.getOneTaskInfo(taskid));
        }catch (Exception e){
            LOGGER.info("异常"+e);
            if (e instanceof GeneralException) {
                return new Result(Integer.parseInt(((GeneralException) e).getErrorCode()), e.getMessage());
            }
            throw new GeneralException("系统错误");
        }
    }

}
