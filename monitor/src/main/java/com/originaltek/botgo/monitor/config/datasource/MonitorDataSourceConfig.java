package com.originaltek.botgo.monitor.config.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.Assert;

import javax.sql.DataSource;

/**
 * @author wangzhu
 */
@MapperScan(basePackages = "com.originaltek.botgo.monitor.dao.mapper", sqlSessionTemplateRef = "monitorSqlSessionTemplate")
public class MonitorDataSourceConfig {

    @Bean(name = "monitorSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("monitorDataSource") DataSource dataSource) throws Exception {
        Assert.notNull(dataSource,"dataSource not conf");
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/monitor/*.xml"));
        bean.setTypeAliasesPackage("com.originaltek.botgo.monitor.dao.bean");
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setUseGeneratedKeys(true);
        configuration.setUseColumnLabel(true);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(true);
        bean.setConfiguration(configuration);
        return bean.getObject();
    }

    @Bean(name = "monitorTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("monitorDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "monitorSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("monitorSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
