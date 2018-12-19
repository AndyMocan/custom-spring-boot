package com.originaltek.botgo.monitor.dao.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName MonitorEntity
 * @Description 监控实体类
 * @Author chen.zhangchao
 * @Date 2018/11/28$ 19:35$
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonitorEntity {


    private int id;
    private String type;
    private int isSuccess;
    private String protocol;
    private String cron;
    private int dataNum;
    private int successDataNum;
    private int errorNum;
    private int tryCount;
    private String message;
    private String notes;


    public MonitorEntity(String cron , String type , String protocol){
        this.cron = cron;
        this.type = type;
        this.protocol = protocol;
        this.isSuccess = -1;
    }

    public MonitorEntity(String type , String protocol){
        this.type = type;
        this.protocol = protocol;
        this.isSuccess = -1;
    }
}