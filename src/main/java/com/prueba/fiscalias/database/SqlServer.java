/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.fiscalias.database;

import java.sql.DriverManager;
import java.sql.Connection;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ruldin
 */
public class SqlServer {

    public Connection connection;
    private final String server = "jdbc:sqlserver://localhost:1433;databaseName=Fiscalias_MP";
    static private final String user = "sa";
    static private final String password = "Test12345.";

    public void open() throws ClassNotFoundException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = (Connection) DriverManager.getConnection(server, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(SqlServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(SqlServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
