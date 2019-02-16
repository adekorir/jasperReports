package com.felix.database;

import java.sql.SQLException;

/**
 *
 * @author Felix
 */
public class App {
    public static void main(String[] args) {
        Database[] databases = {
            new MySQLDatabase("farm", "root", ""),
            new DerbyDatabase("//localhost:1527;databaseName=data/testDB;create=true"),
            new DerbyDatabase("data/petsDB;create=true")
        };
        
        // initialize databases
        for (Database db: databases) {
            try {
                db.init();
            } catch (SQLException e) {
                System.err.printf("Error initializing database: %s %s %n",db, e.getMessage());
            }
        }
        
        // todo: do something with the databases
        
        // tear down the database
        for (Database db: databases) {
            try {
                db.tearDown();
            } catch (SQLException e) {
                System.err.printf("Error tearing down database %s: %s %n", db, e.getMessage());
            }
        }
    }
}
