# spring-boot-datasource-multi-with-flyway-demo
## This is a demo project for
- Spring boot multiple database
- flyway multiple database version control
- reuse mybatis configurations

Please feel free to try.


## multiple datasource configuration
```java
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


```

## multiple database version control configuration
```java
package com.ferriswheel.datasource.multi.config;

import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

/**
 * @author Kenny Fang
 * @since 0.0.1
 */
@Configuration
public class FlywayMultipleDatasourcesConfig {

    public static final String DEFAULT_LOCATION = "db/migration";

    private List<DataSource> dataSourceList;

    public FlywayMultipleDatasourcesConfig(List<DataSource> dataSourceList) {
        this.dataSourceList = dataSourceList;
    }

    @PostConstruct
    public void migrations() {

        dataSourceList.forEach(dataSource -> {
            String jdbcUrl = ((HikariDataSource) dataSource).getJdbcUrl();
            String dbLocation = extractDatabaseNameFromJdbcUrl(jdbcUrl);

            ClassicConfiguration configuration = new ClassicConfiguration();
            configuration.setDataSource(dataSource);
            configuration.setLocationsAsStrings(DEFAULT_LOCATION + dbLocation);
            Flyway flyway = new Flyway(configuration);
            flyway.migrate();
        });
    }

    private String extractDatabaseNameFromJdbcUrl(String jdbcUrl) {
        String[] jdbcUrls = jdbcUrl.split("\\?");

        return jdbcUrls[0].substring(jdbcUrls[0].lastIndexOf('/'));
    }
}

```

## utils
```java
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
```

## application.yml
```yaml
spring:
  datasource:
    ds1:
      jdbcUrl: jdbc:mariadb://localhost:3306/db1?createDatabaseIfNotExist=true&useSSL=false&useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Shanghai&failOverReadOnly=false
      username: root
      password: 654321
      driver-class-name: org.mariadb.jdbc.Driver
    ds2:
      jdbcUrl: jdbc:mariadb://localhost:3306/db2?createDatabaseIfNotExist=true&useSSL=false&useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Shanghai&failOverReadOnly=false
      username: root
      password: 654321
      driver-class-name: org.mariadb.jdbc.Driver
    ds3:
      jdbcUrl: jdbc:mariadb://localhost:3306/db3?createDatabaseIfNotExist=true&useSSL=false&useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Shanghai&failOverReadOnly=false
      username: root
      password: 654321
      driver-class-name: org.mariadb.jdbc.Driver
  flyway:
    enabled: false
mybatis:
  configuration:
    map-underscore-to-camel-case: true
  mapper-locations: classpath:mapper/**/*Mapper.xml
```

## important
It is important to new instance mybatis *Configuration*. Mybatis *Configuration* has a *Environment* and this class has a *Datasource*, **if we reuse the same *Configuration* instance, it will be override by the last *Datasource***. So I new instance for every datasource and copy all configurations from the default one. if you still find issues, please feel free to fix it.
```java
org.apache.ibatis.session.Configuration configuration = new  org.apache.ibatis.session.Configuration();
BeanUtils.copyProperties(mybatisProperties.getConfiguration(), configuration);
```

>(c) Kenny Fang
