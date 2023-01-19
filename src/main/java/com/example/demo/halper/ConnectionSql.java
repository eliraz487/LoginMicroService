package com.example.demo.halper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class ConnectionSql {

    private static final String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String username="sa";
    private static final String password="1234";
    private static final String url="jdbc:sqlserver://localhost:1433;databaseName=Login";

    public static Connection getConntion() throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        Connection conn;
        conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
}
