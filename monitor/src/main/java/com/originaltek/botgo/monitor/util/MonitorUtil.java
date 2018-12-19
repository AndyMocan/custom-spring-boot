package com.originaltek.botgo.monitor.util;

import com.originaltek.botgo.monitor.dao.bean.MonitorEntity;
import com.originaltek.botgo.monitor.dao.bean.TaskEntity;
import com.originaltek.botgo.monitor.service.TaskService;
import com.originaltek.botgo.monitor.service.impl.TaskServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @create_time : 2018/10/12 15:26
 * @author      : chen.zhangchao
 * @todo        :
 */
@Slf4j
public class MonitorUtil {

    /**
     * 监控Service用到的协议
     */
    public static final String CONST_PROTOCAL_HTTP = "HTTP";
    public static final String CONST_PROTOCAL_HTTPS = "HTTPS";
    public static final String CONST_PROTOCAL_WEBSERVICE = "WEBSERVICE";
    public static final String CONST_PROTOCAL_RABBITMQ = "RABBITMQ";
    public static final String CONST_PROTOCAL_DATABASE = "DATABASE";
    public static final String CONST_PROTOCAL_TIMED_TASK = "TIMED_TASK";


    /**
     * 执行定时任务
     * @param taskEntity
     */
    public static void executeTask(TaskEntity taskEntity){
        TaskService taskService = ApplicationContextHolder.getBean(TaskService.class);
        if(TaskServiceImpl.WORKING_STATE.equals(taskEntity.getWorking())){
            taskService.run(taskEntity , new MonitorEntity(taskEntity.getCron(),
                    taskEntity.getName(),
                    MonitorUtil.CONST_PROTOCAL_TIMED_TASK));
        }else{
            log.info("该环境未开启" + taskEntity.getName() + "定时任务");
        }
    }


    public static String execute(TaskEntity taskEntity , String type){
        TaskService taskService = ApplicationContextHolder.getBean(TaskService.class);
        return taskService.run(taskEntity , new MonitorEntity("" ,
                taskEntity.getName() ,
                type));
    }


}
