package com.originaltek.botgo.monitor.service;


import com.originaltek.botgo.monitor.dao.bean.MonitorEntity;

/**
 * @author chen.zhangchao
 * @date 2018/9/29 0029
 */
public interface MonitorService {

    /**
     * 创建录入一个新的定时任务
     * @param monitorEntity
     * @return
     */
    int addNewMonitor(MonitorEntity monitorEntity);


    /**
     * 更新定时任务的状态
     * @param monitorEntity
     */
    void updateState(MonitorEntity monitorEntity);

}
