package com.sqltestdataapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConfig {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.dataSource.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${driver-path}")
    private String driverPath;

    public String getDatasourceUrl() {
        return datasourceUrl;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDriverPath() {
        return driverPath;
    }

}
