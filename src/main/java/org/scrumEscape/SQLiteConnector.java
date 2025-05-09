package org.scrumEscape;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnector {

    private static final String DB_URL = "jdbc:sqlite:database.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("verbonden met database");
        } catch (SQLException e) {
            System.out.println("error bij verbinden met database: " + e.getMessage());
        }
        return conn;
    }
}