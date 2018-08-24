package com.demo.web.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: parent
 * @description: 定时任务
 * @author: zouweidong
 * @create: 2018-08-22 14:09
 **/
@Component
public class SchedulerTask {
    Logger logger = LoggerFactory.getLogger(SchedulerTask.class);

    /*
    @Scheduled(fixedRate = 6000) ：上一次开始执行时间点之后6秒再执行；
    @Scheduled(fixedDelay = 6000) ：上一次执行完毕时间点之后6秒再执行；
    @Scheduled(initialDelay=1000, fixedRate=6000) ：第一次延迟1秒后执行，之后按fixedRate的规则每6秒执行一次。
    */

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime()
    {
        logger.info("现在时间："+ dateFormat.format(new Date()));
    }
}
