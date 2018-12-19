package com.originaltek.botgo.monitor.service.impl;


import com.originaltek.botgo.monitor.dao.bean.MonitorEntity;
import com.originaltek.botgo.monitor.dao.bean.TaskEntity;
import com.originaltek.botgo.monitor.service.MonitorService;
import com.originaltek.botgo.monitor.service.MonitorTaskService;
import com.originaltek.botgo.monitor.service.TaskService;
import com.originaltek.botgo.monitor.util.ApplicationContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TaskServiceImpl
 * @Description 任务调度类
 * @Author chen.zhangchao
 * @Date 2018/11/28$ 19:40$
 * @Version 1.0
 **/
@Service
@Slf4j
@Primary
public class TaskServiceImpl implements TaskService {

    /**
     * 任务在环境中是否是开启状态
     */
    public static final String WORKING_STATE = "1";

    /**
     * 工作是否可以并发执行
     */
    public static final String WORKING_MULTI = "1";


    private static Map<String,Boolean> RunningMap = new HashMap<>(32);

    @Autowired
    private MonitorService monitorService;

    @Override
    public void run(MonitorTaskService monitorTaskService , MonitorEntity monitorEntity) {
        monitorService.addNewMonitor(monitorEntity);
        try {
            monitorTaskService.execute(monitorEntity);
            monitorEntity.setIsSuccess(1);
        } catch (Exception e) {
            log.error(e.getMessage() , e);
            monitorEntity.setIsSuccess(0);
            monitorEntity.setMessage(e.getMessage().substring(0, 1999));
        }
        monitorService.updateState(monitorEntity);
    }

    @Override
    public String run(TaskEntity taskEntity , MonitorEntity monitorEntity) {
        String msg = "success";
        MonitorTaskService monitorTaskService = ApplicationContextHolder.getBean(taskEntity.getBean());
        if(monitorTaskService != null){
            String taskName = taskEntity.getName();
            String multi = taskEntity.getMulti();
            if(!WORKING_MULTI.equals(multi)){
                if(!RunningMap.containsKey(taskName)){
                    RunningMap.put(taskName , true);
                }else{
                    boolean bool = MapUtils.getBooleanValue(RunningMap , taskName);
                    if(bool){
                        msg = "有正在执行的" + taskName + "，本次不执行";
                        log.warn(msg);
                        return msg;
                    }
                }
            }
            run(monitorTaskService ,monitorEntity);
            if(!WORKING_MULTI.equals(multi)){
                RunningMap.put(taskName ,false);
            }
        }else{
            msg = "定时任务" + taskEntity.getName() + "无法进行，缺失Bean:" + taskEntity.getBean();
            log.error(msg);
        }
        return msg;
    }


}