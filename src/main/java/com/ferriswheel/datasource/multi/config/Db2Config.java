package com.ferriswheel.datasource.multi.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

import static com.ferriswheel.datasource.multi.utils.DbConfigUtils.getSqlSessionFactory;

@MapperScan(basePackages="com.ferriswheel.datasource.multi.mapper.db2",
        sqlSessionFactoryRef = "ds2SqlSessionFactory")
@Configuration
public class Db2Config {

    @ConfigurationProperties(prefix="spring.datasource.ds2")
    @Bean
    public DataSource ds2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory ds2SqlSessionFactory(@Qualifier("ds2DataSource") DataSource ds2DataSource,
                                                  MybatisProperties mybatisProperties) throws Exception {
        return getSqlSessionFactory(ds2DataSource, mybatisProperties);
    }

    @Bean
    public TransactionManager ds2TransactionManager(@Qualifier("ds2DataSource") DataSource ds2DataSource) {
        return new DataSourceTransactionManager(ds2DataSource);
    }

    @Bean
    public SqlSessionTemplate ds2SqlSessionTemplate(@Qualifier("ds2SqlSessionFactory") SqlSessionFactory ds2SqlSessionFactory) {
        return new SqlSessionTemplate(ds2SqlSessionFactory);
    }

}

