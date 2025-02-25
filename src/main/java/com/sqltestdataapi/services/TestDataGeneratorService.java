/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright ${project.inceptionYear}-2021 the original author or authors.
 */
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
