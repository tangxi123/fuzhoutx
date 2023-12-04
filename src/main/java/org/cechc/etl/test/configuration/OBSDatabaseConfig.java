package org.cechc.etl.test.configuration;

import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.cechc.etl.test.plugin.SqlInterceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"org.cechc.etl.test.obsmapper"},sqlSessionFactoryRef = "obsSqlSession")
public class OBSDatabaseConfig {

    @Bean(name = "obsSqlSession")
    public SqlSessionFactory obsSqlSession() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(obsDataSource());
        org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
        config.setCallSettersOnNulls(true);
        config.setLogImpl(Slf4jImpl.class);
        config.addInterceptor(new SqlInterceptor());
        factoryBean.setConfiguration(config);
        return factoryBean.getObject();
    }
    @Bean(name = "obsDataSource")
    public DataSource obsDataSource(){
        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://localhost/obs_multiple_sources")
                .username("postgres")
                .password("tx123456")
                .build();
        return dataSource;
    }



}
