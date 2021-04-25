package com.sqltestdataapi;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DataSourceConfig {

    // private final Environment environment;

    // @Value("${server.port}")
    private String port;

    public DataSourceConfig(Environment environment) {
        this.port = environment.getProperty("${server.port}");
    }

    @Bean
    public Object createTable(DataSource dataSource) {
        SqlExecutor sqlExecutor = new SqlExecutor(dataSource);
        sqlExecutor.execute("CREATE TABLE GuitarHero\n" +
                "(\n" +
                "    id         INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    first_name VARCHAR(250) NOT NULL,\n" +
                "    last_name  VARCHAR(250) NOT NULL\n" +
                ")");
        // sqlExecutor.execute("INSERT INTO GuitarHero VALUES (1, 'Tosin', 'Abasi')");
        sqlExecutor.execute("INSERT INTO GUITARHERO VALUES (1, 'Tosin', 'Abasi')");
        return new Object();
    }

    @Bean
    public DataSource dataSource(DatabaseConfig databaseConfig) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, MalformedURLException {
        configDriver(databaseConfig);
        HikariConfig hikariConfig = new HikariConfig();
        // à récupérer depuis properties
        hikariConfig.setJdbcUrl("jdbc:h2:mem:test");
        hikariConfig.setUsername("user");
        hikariConfig.setPassword("pwd");
        hikariConfig.setDriverClassName("org.h2.Driver");

        return new HikariDataSource(hikariConfig);
    }

    private void configDriver(DatabaseConfig databaseConfig) throws ClassNotFoundException, SQLException, MalformedURLException, IllegalAccessException, InstantiationException {
        String datasourceUrl = databaseConfig.datasourceUrl;
        System.out.println("datasourceUrl = " + datasourceUrl);

        System.out.println("REPORT" + this.port);
        URL url = new URL("jar:file:/home/fab/Téléchargements/h2-1.4.200.jar!/"); // à ajouter dans properties
        // URL url = new URL(environment.getProperty("driver-path")); // à ajouter dans properties
        String className = "org.h2.Driver";// à ajouter dans properties
        URLClassLoader ucl = URLClassLoader.newInstance(new URL[]{url});
        Driver driver = (Driver) Class.forName(className, true, ucl).newInstance();
        DriverManager.registerDriver(new DriverShim(driver));
        DriverManager.getConnection("jdbc:h2:mem:test", "user", "pwd");// à ajouter dans properties
    }

/*
    @Bean
    public String getPort(Environment environment){
         return environment.getProperty("${local.server.port}");
    }
*/
}