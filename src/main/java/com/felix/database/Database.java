package com.felix.database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Felix
 */
public interface Database {
    
    default void init() throws SQLException {}
    Connection connect() throws SQLException;
    default void tearDown() throws SQLException {}
}
