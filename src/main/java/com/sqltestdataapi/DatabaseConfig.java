package com.sqltestdataapi;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;


@Component
public class DatabaseConfig {

    @Value("${spring.datasource.url}")
    String datasourceUrl;

    @Value("${spring.dataSource.driverClassName}")
    String driverClassName;

    @Value("${spring.datasource.url}")
    String url;

    @Value("${spring.datasource.username}")
    String user;

    @Value("${spring.datasource.password}")
    String password;

    public String getDatasourceUrl() {
        return datasourceUrl;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    /*
*/

}
