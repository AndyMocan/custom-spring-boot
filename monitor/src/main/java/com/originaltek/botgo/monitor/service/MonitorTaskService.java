package com.originaltek.botgo.monitor.service;


import com.originaltek.botgo.monitor.dao.bean.MonitorEntity;

/**
 * @ClassName MonitorTaskService
 * @Description 允许监控模块进行监控的接口  在执行execute 方法的过程中，如果遇到程序错误，请抛出异常，别吞异常，抛出异常视为执行失败，其余情况均为执行成功
 * @Author chen.zhangchao
 * @Date 2018/11/28$ 19:41$
 * @Version 1.0
 **/
public interface MonitorTaskService {

    /**
     * 执行定时任务
     *
     */
    void execute(MonitorEntity monitorEntity) throws Exception;

}