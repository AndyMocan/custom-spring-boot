package com.originaltek.botgo.monitor.service;

import com.originaltek.botgo.monitor.dao.bean.MonitorEntity;
import com.originaltek.botgo.monitor.dao.bean.TaskEntity;

/**
 * @ClassName TaskService
 * @Description 定时任务调度类
 * @Author chen.zhangchao
 * @Date 2018/11/28$ 19:39$
 * @Version 1.0
 **/
public interface TaskService {

    /**
     * 执行定时任务
     * @param monitorTaskService
     * @param monitorEntity
     */
    void run(MonitorTaskService monitorTaskService, MonitorEntity monitorEntity);

    /**
     * 执行定时任务
     * @param taskEntity
     */
    String run(TaskEntity taskEntity, MonitorEntity monitorEntity);
}