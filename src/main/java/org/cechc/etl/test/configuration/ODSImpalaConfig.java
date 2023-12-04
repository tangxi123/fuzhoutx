package org.cechc.etl.test.configuration;

import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.cechc.etl.test.handler.MyLocalDateTimeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//@Configuration
//@MapperScan(basePackages = {"org.cechc.etl.test.odsmapper"},sqlSessionFactoryRef = "odsImpalaSqlSession")
//public class ODSImpalaConfig {
//    @Bean(name = "odsImpalaSqlSession")
//    public SqlSessionFactory obsSqlSession() throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(obsDataSource());
//
//        org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
//        config.setCallSettersOnNulls(true);
//        config.setLogImpl(Slf4jImpl.class);
//        config.setDefaultEnumTypeHandler(MyLocalDateTimeHandler.class);
//        factoryBean.setConfiguration(config);
//
//        return factoryBean.getObject();
//    }
//    @Bean(name = "odsImpalaDataSource")
//    public DataSource obsDataSource(){
//        DataSource dataSource = DataSourceBuilder.create()
//                .driverClassName("com.cloudera.impala.jdbc.Driver")
//                .url("jdbc:impala://172.16.56.55:21050/default")
//                .username("hdfs")
//                .password("hdfs")
//                .build();
//        return dataSource;
//    }
//
//}
