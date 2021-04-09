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
 * Copyright 2021-2021 the original author or authors.
 */
package com.sqltestdataapi;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class SqlExecutor {

    private final DataSource dataSource;

    SqlExecutor(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    void execute(List<String> queries) {
        for (String query : queries) {
            execute(query);
        }
    }

    void execute(String sql) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException("Unable to execute " + sql, e);
        }
    }

    String executeSelect(String select) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(select)) {
            ResultSet resultSet = statement.executeQuery();
            StringBuilder resultBuilder = new StringBuilder();
            String header = "";
            while (resultSet.next()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                if (header.isEmpty()) {
                    header = buildHeaderFrom(metaData);
                    resultBuilder.append(header);
                    resultBuilder.append(System.lineSeparator());
                }
                String columnValues = buildColumnsValuesFrom(resultSet, metaData);
                resultBuilder.append(columnValues);
                resultBuilder.append(System.lineSeparator());
            }
            return resultBuilder.toString();
        } catch (SQLException e) {
            throw new IllegalStateException("Unable to execute " + select, e);
        }

    }

    private String buildHeaderFrom(ResultSetMetaData metaData) throws SQLException {
        int columnCount = metaData.getColumnCount();
        String header = "";
        for (int i = 1; i <= columnCount; i++) {
            header += metaData.getColumnName(i);
            if (i != columnCount) {
                header += "*";
            }
        }
        return header;
    }

    private String buildColumnsValuesFrom(ResultSet resultSet, ResultSetMetaData metaData) throws SQLException {
        int columnCount = metaData.getColumnCount();
        String columnValues = "";
        for (int i = 1; i <= columnCount; i++) {
            Object columnValue = resultSet.getObject(i);
            columnValues += columnValue;
            if (i != columnCount) {
                columnValues += "*";
            }
        }
        return columnValues;
    }

}
