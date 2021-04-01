package com.sqltestdataapi.services;

import org.quickperf.sqldata.SqlTestDataGenerator;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service("testDataGeneratorService")
public class TestDataGeneratorService {

    private final DataSource dataSource;

    public TestDataGeneratorService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String generateTestQuery(String query) {
        // DataSource dataSource = DataSourceBuilder.INSTANCE.build("jdbc:h2:mem:test", "power", "ranger");
        SqlTestDataGenerator sqlTestDataGenerator = SqlTestDataGenerator.buildFrom(dataSource);
        String script = sqlTestDataGenerator.generateDatasetScriptFor(query);

        return script;
    }

}
