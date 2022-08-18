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
