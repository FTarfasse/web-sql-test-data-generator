package com.sqltestdataapi;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;


@Component
public class DatabaseConfig {

    @Value("${spring.datasource.url}")
    String datasourceUrl;



}
