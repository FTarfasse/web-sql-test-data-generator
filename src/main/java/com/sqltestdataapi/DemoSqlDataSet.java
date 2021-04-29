package com.sqltestdataapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Profile("demo")
@Component
public class DemoSqlDataSet implements CommandLineRunner {

    private final DataSource dataSource;

    public DemoSqlDataSet(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        executeSql("CREATE TABLE GuitarHero\n" +
                "(\n" +
                "    id         INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    first_name VARCHAR(250) NOT NULL,\n" +
                "    last_name  VARCHAR(250) NOT NULL\n" +
                ")");
        executeSql("INSERT INTO GUITARHERO VALUES (1, 'Tosin', 'Abasi')");
    }

    void executeSql(String sql) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException("Unable to execute " + sql, e);
        }
    }

}
