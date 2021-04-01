package com.sqltestdataapi;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DataSourceBuilder {

        public static final DataSourceBuilder INSTANCE = new DataSourceBuilder();

        private DataSourceBuilder() { }

         public DataSource build(String jdbcUrl, String dbUserName, String dbPassword) {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(jdbcUrl);
            hikariConfig.setUsername(dbUserName);
            hikariConfig.setPassword(dbPassword);

            return new HikariDataSource(hikariConfig);
        }

}
