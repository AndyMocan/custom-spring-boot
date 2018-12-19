package com.originaltek.botgo.monitor.service.impl;


import com.originaltek.botgo.monitor.dao.bean.MonitorEntity;
import com.originaltek.botgo.monitor.dao.mapper.MonitorMapper;
import com.originaltek.botgo.monitor.service.MonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chen.zhangchao
 * @date 2018/9/29 0029
 */
@Service
public class MonitorServiceImpl implements MonitorService {

    public static final Logger logger = LoggerFactory.getLogger(MonitorServiceImpl.class);

    /**
     * 开始某项任务
     */
    public static final int CONST_STATE_TODO = 2;
    /**
     * 处理成功
     */
    public static final int CONST_STATE_SUCCESS = 1;

    /**
     * 处理失败
     */
    public static final int CONST_STATE_ERROR = 0;


    @Autowired
    private MonitorMapper monitorMapper;


    @Override
    public int addNewMonitor(MonitorEntity monitorEntity) {

        return monitorMapper.addNewMonitorRecord(monitorEntity);
    }

    @Override
    public void updateState(MonitorEntity monitorEntity) {
        if(monitorEntity.getId() == 0){
            logger.error("无主键");
            return ;
        }
        monitorMapper.updateMonitorInfo(monitorEntity);
    }
}
