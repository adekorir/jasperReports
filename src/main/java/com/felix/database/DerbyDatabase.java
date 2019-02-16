package com.felix.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Felix
 */
public class DerbyDatabase implements Database {
    
    private final String URL;
    
    public DerbyDatabase(String url) {
        this.URL = url;
    }

    @Override
    public void init() throws SQLException {
        if (URL.startsWith("//localhost")) {
        }
    }

    
    @Override
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(String.format("jdbc:derby:%s", URL));
    }

    @Override
    public void tearDown() throws SQLException {
    }

    @Override
    public String toString() {
        return "DerbyDatabase: " + URL;
    }
    
}
