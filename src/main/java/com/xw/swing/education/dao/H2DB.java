
package com.xw.swing.education.dao;

import java.sql.*;

public class H2DB {
    private final static String url = "jdbc:h2:file:~/.h2/db/education;AUTO_SERVER=TRUE";
    private final static String user = "sa";
    private final static String password = "";

    private H2DB() {
    }

    private static Connection CONN;
    private static H2DB H2DB;

    public static H2DB getInstant() {
        if (H2DB == null) {
            return new H2DB();
        }
        return H2DB;
    }

    public Connection getConn() {

        try {
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return CONN;
    }

    public static void closeConn(Statement statement, Connection connection) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

