package com.sqltestdataapi.services;

import org.springframework.stereotype.Service;
import org.stdg.SqlTestDataGenerator;

import javax.sql.DataSource;

@Service("testDataGeneratorService")
public class TestDataGeneratorService {

    private final DataSource dataSource;

    public TestDataGeneratorService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String generateTestQuery(String query) {
        SqlTestDataGenerator sqlTestDataGenerator = SqlTestDataGenerator.buildFrom(dataSource);
        String insertScript = sqlTestDataGenerator.generateInsertScriptFor(query);
        return insertScript;
    }

}
