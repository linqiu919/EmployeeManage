package com.java.sm.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.xml.ws.Action;

/**
 * @author 722A-08-CXB
 * @version 1.0.0
 * @ClassName ServiceConfig.java
 * @CreateTime 2021年06月14日 20:51:00
 */
@Configuration
//注解方式配置包扫描
@ComponentScan(basePackages = {"com.java.sm.service"})
@EnableTransactionManagement //开启事务注解驱动
public class ServiceConfig {

    @Autowired
    private DruidDataSource druidDataSource;

    //配置事物管理器
    @Bean
    public DataSourceTransactionManager transactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(druidDataSource);

        return dataSourceTransactionManager;
    }


}
