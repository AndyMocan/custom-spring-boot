package com.originaltek.botgo.monitor.autoconfigure;

import com.alibaba.druid.pool.DruidDataSource;
import com.originaltek.botgo.monitor.config.datasource.MonitorDataSourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;


/**
 * @author wangzhu
 */
@Configuration
@ConditionalOnProperty(prefix = "monitor", value = "enabled", havingValue = "true")
@EnableConfigurationProperties({MonitorProperties.class})
@AutoConfigureAfter({MonitorDataSourceConfig.class})
public class MonitorAutoConfigure implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(MonitorAutoConfigure.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("MonitorAutoConfigure init ok");
    }

    @Bean(name = "monitorDataSource")
    public DataSource testDataSource(@Autowired MonitorProperties monitorProperties) throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(monitorProperties.getUsername());
        dataSource.setPassword(monitorProperties.getPassword());
        dataSource.setUrl(monitorProperties.getJdbcUrl());
        dataSource.setDriverClassName(monitorProperties.getDriverClass());
        dataSource.setFilters(monitorProperties.getFilters());
        dataSource.setInitialSize(monitorProperties.getInitialSize());
        dataSource.setMaxActive(monitorProperties.getMaxActive());
        dataSource.setMinIdle(monitorProperties.getMinIdle());
        dataSource.setMaxIdle(monitorProperties.getMaxIdle());
        dataSource.setMaxWait(monitorProperties.getMaxWait());
        dataSource.setTimeBetweenEvictionRunsMillis(monitorProperties.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(monitorProperties.getMinEvictableIdleTimeMillis());
        dataSource.setValidationQuery(monitorProperties.getValidationQuery());
        dataSource.setTestOnBorrow(monitorProperties.isTestOnBorrow());
        dataSource.setTestOnReturn(monitorProperties.isTestOnReturn());
        dataSource.setTestWhileIdle(monitorProperties.isTestWhileIdle());
        dataSource.setPoolPreparedStatements(monitorProperties.isPoolPreparedStatements());
        dataSource.setMaxOpenPreparedStatements(monitorProperties.getMaxOpenPreparedStatements());
        return dataSource;
    }


}
