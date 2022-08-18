package com.ferriswheel.datasource.multi.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

@MapperScan(basePackages="com.ferriswheel.datasource.multi.mapper.db3",
        sqlSessionFactoryRef = "ds3SqlSessionFactory")
@Configuration
public class Db3Config {

    @ConfigurationProperties(prefix="spring.datasource.ds3")
    @Bean
    public DataSource ds3DataSource() {
        return DataSourceBuilder.create().build();
    }


//    @Bean
//    @ConfigurationProperties(prefix = "mybatis.configuration")
//    public org.apache.ibatis.session.Configuration configuration() {
//        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
//        configuration.setMapUnderscoreToCamelCase(true);
//        return configuration;
//    }

    @Bean
    public SqlSessionFactory ds3SqlSessionFactory(@Qualifier("ds3DataSource") DataSource ds3DataSource,
                                                  MybatisProperties mybatisProperties) throws Exception {
        org.apache.ibatis.session.Configuration configuration = new  org.apache.ibatis.session.Configuration();
        BeanUtils.copyProperties(mybatisProperties.getConfiguration(), configuration);
        configuration.setEnvironment(null);
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(ds3DataSource);
        sqlSessionFactoryBean.setMapperLocations(mybatisProperties.resolveMapperLocations());
        sqlSessionFactoryBean.setConfiguration(configuration);
//        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);

        return sqlSessionFactoryBean.getObject();
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

