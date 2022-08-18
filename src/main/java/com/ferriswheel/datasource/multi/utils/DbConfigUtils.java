package com.ferriswheel.datasource.multi.utils;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.BeanUtils;

import javax.sql.DataSource;

/**
 * @author Kenny Fang
 * @since 0.0.1
 */
public interface DbConfigUtils {

    static SqlSessionFactory getSqlSessionFactory(DataSource ds1DataSource, MybatisProperties mybatisProperties) throws Exception {
        org.apache.ibatis.session.Configuration configuration = new  org.apache.ibatis.session.Configuration();
        BeanUtils.copyProperties(mybatisProperties.getConfiguration(), configuration);
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(ds1DataSource);
        sqlSessionFactoryBean.setMapperLocations(mybatisProperties.resolveMapperLocations());
        sqlSessionFactoryBean.setConfiguration(configuration);

        return sqlSessionFactoryBean.getObject();
    }
}
