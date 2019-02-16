package com.felix.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Felix
 */
public class MySQLDatabase implements Database {
    
    final String name, user, pass;
    public MySQLDatabase(String dbName, String user, String password) {
        this.name = dbName;
        this.user = user;
        this.pass = password;
    }

    @Override
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(String.format("jdbc:mhysql://localhost:3306/%s", name), user, pass);
    }
}
