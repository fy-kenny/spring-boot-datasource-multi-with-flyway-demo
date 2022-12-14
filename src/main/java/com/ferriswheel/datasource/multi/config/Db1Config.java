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
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

import static com.ferriswheel.datasource.multi.utils.DbConfigUtils.getSqlSessionFactory;

@MapperScan(basePackages="com.ferriswheel.datasource.multi.mapper.db1",
        sqlSessionFactoryRef = "ds1SqlSessionFactory")
@Configuration
public class Db1Config {

    @ConfigurationProperties(prefix="spring.datasource.ds1")
    @Bean
    @Primary
    public DataSource ds1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public SqlSessionFactory ds1SqlSessionFactory(@Qualifier("ds1DataSource") DataSource ds1DataSource,
                                                  MybatisProperties mybatisProperties) throws Exception {

        return getSqlSessionFactory(ds1DataSource, mybatisProperties);
    }

    @Bean
    @Primary
    public TransactionManager ds1TransactionManager(@Qualifier("ds1DataSource") DataSource ds1DataSource) {
        return new DataSourceTransactionManager(ds1DataSource);
    }

    @Bean
    @Primary
    public SqlSessionTemplate ds1SqlSessionTemplate(@Qualifier("ds1SqlSessionFactory") SqlSessionFactory ds1SqlSessionFactory) {
        return new SqlSessionTemplate(ds1SqlSessionFactory);
    }

}

