package com.sqltestdataapi;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Inspiration from https://www.kfu.com/~nsayer/Java/dyn-jdbc.html
 * with the authorization of Nick Sayer.
 */
public class DriverShim implements Driver {

    private Driver driver;

    // No arg contructor required by Hikari
    public DriverShim() {
    }

    public DriverShim(Driver driver) {
        this.driver = driver;
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        return this.driver.connect(url, info);
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return this.driver.acceptsURL(url);
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return this.driver.getPropertyInfo(url, info);
    }

    @Override
    public int getMajorVersion() {
        return this.driver.getMajorVersion();
    }

    @Override
    public int getMinorVersion() {
        return this.driver.getMinorVersion();
    }

    @Override
    public boolean jdbcCompliant() {
        return this.driver.jdbcCompliant();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return this.driver.getParentLogger();
    }
}
