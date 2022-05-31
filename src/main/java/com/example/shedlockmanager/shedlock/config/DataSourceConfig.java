package com.example.shedlockmanager.shedlock.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Autowired
    private MySQLConfig mySQLConfig;

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder =
                DataSourceBuilder.create()
                        .url(mySQLConfig.getUrl())
                        .username(mySQLConfig.getUsername())
                        .password(mySQLConfig.getPassword());

        return dataSourceBuilder.build();
    }
}
