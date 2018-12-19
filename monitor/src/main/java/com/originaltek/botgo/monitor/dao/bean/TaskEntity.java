package com.originaltek.botgo.monitor.dao.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * create_time : 2018/10/12 15:47
 * @author      : chen.zhangchao
 * todo        :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {

    /**
     * Cron 表达式
     */
    private String cron;

    /**
     * 任务的名称
     */
    private String name;

    /**
     * Bean的名称
     */
    private String bean;

    /**
     * 该环境是否开启该任务
     */
    private String working;
    /**
     * 是否可以多个任务在同一个时间段执行
     */
    private String multi;

}
