package com.demo.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.entity.AdminUser;
import com.demo.entity.common.Result;
import com.demo.service.iservice.ICommentInfoSV;
import com.demo.utils.common.ResultUtil;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: parent
 * @description: 任务评论接口
 * @author: zhangchen
 * @create: 2018-8-31 10:25
 */

@RestController
@RequestMapping("/mytodo/comment")
public class TaskCommentController {
    private Logger LOGGER = LoggerFactory.getLogger(TaskCommentController.class);

    @Reference
    ICommentInfoSV iCommentInfoSV;

    @GetMapping("/getTaskCommentList/{taskid}")
    public Result getTaskCommentList(@RequestAttribute(name = "loginUser") AdminUser loginUser, @PathVariable(name = "taskid") String taskId) {


        LOGGER.info("loginUser" + loginUser.getUsername());
        LOGGER.info("taskid-->" + taskId);
        return ResultUtil.success(iCommentInfoSV.getTaskCommentList(taskId));
    }

    @GetMapping("/addTaskComment")
    public Result addTaskComment(@RequestAttribute(name = "loginUser") AdminUser loginUser, @RequestParam(name = "taskId") String taskId, @RequestParam(name = "comment") String comment) {

        LOGGER.info("loginUser" + loginUser.getUsername());
        LOGGER.info("taskId-->" + taskId);
        try {
            if (iCommentInfoSV.addTaskComment(taskId, comment, loginUser.getUsername())) {
                LOGGER.info("添加评论成功：taskId=" + taskId);
                return ResultUtil.success(2001, "添加评论成功");
            } else {
                LOGGER.info("添加评论失败：taskId=" + taskId);
                return ResultUtil.error(2002, "添加评论失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("添加评论异常：taskId=" + taskId);
            return ResultUtil.error(2003, "添加评论异常");
        }
    }

    @GetMapping("/delTaskComment/{id}")
    public Result delTaskComment(@RequestAttribute(name = "loginUser") AdminUser loginUser, @PathVariable(name = "id") Integer id) {
        LOGGER.info("loginUser" + loginUser.getUsername());
        LOGGER.info("id--" + id);
        if (iCommentInfoSV.delTaskComment( id, loginUser.getUsername())==-1){
            LOGGER.info("当前用户无权限，删除评论失败：id=" +id);
            return ResultUtil.error(3003, "当前用户无权限，删除评论失败");
        }
        else if(iCommentInfoSV.delTaskComment( id, loginUser.getUsername())==0)
        {
            LOGGER.info("删除评论失败：id=" +id);
            return ResultUtil.error(3002, "删除评论失败");
        }
        else{
            LOGGER.info("删除评论成功：id=" + id);
            return ResultUtil.success(3001, "删除评论成功");
        }
    }
}