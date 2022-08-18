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

@MapperScan(basePackages="com.ferriswheel.datasource.multi.mapper.db3",
        sqlSessionFactoryRef = "ds3SqlSessionFactory")
@Configuration
public class Db3Config {

    @ConfigurationProperties(prefix="spring.datasource.ds3")
    @Bean
    public DataSource ds3DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory ds3SqlSessionFactory(@Qualifier("ds3DataSource") DataSource ds3DataSource,
                                                  MybatisProperties mybatisProperties) throws Exception {
        return getSqlSessionFactory(ds3DataSource, mybatisProperties);
    }

    @Bean
    public TransactionManager ds3TransactionManager(@Qualifier("ds3DataSource") DataSource ds3DataSource) {
        return new DataSourceTransactionManager(ds3DataSource);
    }

    @Bean
    public SqlSessionTemplate ds3SqlSessionTemplate(@Qualifier("ds3SqlSessionFactory") SqlSessionFactory ds3SqlSessionFactory) {
        return new SqlSessionTemplate(ds3SqlSessionFactory);
    }

}

