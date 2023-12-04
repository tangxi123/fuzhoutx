package org.cechc.etl.test.configuration;

import org.apache.commons.logging.Log;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.apache.ibatis.logging.log4j2.Log4j2LoggerImpl;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.slf4j.SLF4JLogger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"org.cechc.etl.test.mapper"},sqlSessionFactoryRef = "sqlSession")
public class DatabaseConfig {
    @Bean(name = "sqlSession")
    public SqlSessionFactory obsSqlSession() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(obsDataSource());
        org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
        config.setCallSettersOnNulls(true);
        config.setLogImpl(Slf4jImpl.class);
        factoryBean.setConfiguration(config);
        return factoryBean.getObject();
    }
    @Bean(name = "dataSource")
    public DataSource obsDataSource(){
        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/etl_test_tool")
                .username("root")
                .password("tx123456")
                .build();
        return dataSource;
    }
}
