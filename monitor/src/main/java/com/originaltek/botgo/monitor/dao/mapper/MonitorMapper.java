package com.originaltek.botgo.monitor.dao.mapper;

import com.originaltek.botgo.monitor.dao.bean.MonitorEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chen.zhangchao
 * @date 2018/9/29 0029
 */
@Mapper
public interface MonitorMapper {
    /**
     * 插入一个新的监控记录
     * @param monitorEntity
     * @return
     */
    int addNewMonitorRecord(MonitorEntity monitorEntity);

    /**
     * 更新执行情况
     * @param monitorEntity
     */
    void updateMonitorInfo(MonitorEntity monitorEntity);

}
