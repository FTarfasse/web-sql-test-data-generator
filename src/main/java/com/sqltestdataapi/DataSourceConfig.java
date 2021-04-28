package com.sqltestdataapi;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DataSourceConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiApplication.class);

    @Bean
    public Object createTable(DataSource dataSource) {
        SqlExecutor sqlExecutor = new SqlExecutor(dataSource);
        sqlExecutor.execute("CREATE TABLE GuitarHero\n" +
                "(\n" +
                "    id         INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    first_name VARCHAR(250) NOT NULL,\n" +
                "    last_name  VARCHAR(250) NOT NULL\n" +
                ")");
        sqlExecutor.execute("INSERT INTO GUITARHERO VALUES (1, 'Tosin', 'Abasi')");

        return new Object();
    }

    @Bean
    public DataSource dataSource(DatabaseConfig databaseConfig) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, MalformedURLException {

        try {
            configDriver(databaseConfig);

            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(databaseConfig.getDatasourceUrl());
            hikariConfig.setUsername(databaseConfig.getUser());
            hikariConfig.setPassword(databaseConfig.getPassword());
            hikariConfig.setDriverClassName(DriverShim.class.getCanonicalName());
            return new HikariDataSource(hikariConfig);
        } catch (Exception e) {
            LOGGER.error("Database configuration error");
            throw e;
        }
    }

    private void configDriver(DatabaseConfig databaseConfig) throws ClassNotFoundException, SQLException, MalformedURLException, IllegalAccessException, InstantiationException {
        String driverPath = databaseConfig.getDriverPath();
        String urlSpecification = "jar:file:/" + driverPath + "!/";
        URL url = new URL(urlSpecification);
        // dynamic load of the db driver
        String className = databaseConfig.getDriverClassName();
        URLClassLoader ucl = URLClassLoader.newInstance(new URL[]{url});
        Driver driver = (Driver) Class.forName(className, true, ucl).newInstance();
        DriverManager.registerDriver(new DriverShim(driver));
    }

}