package com.t2404e.wellcometojavaweb.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class MysqlConnector {
    // JDBC URL khuyến nghị (Unicode, timezone, tắt SSL cục bộ)
    private static final String URL = "jdbc:mysql://localhost:3306/hello_t2404e";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    private MysqlConnector() {}

    /** Lấy connection: mở mới nếu null hoặc đã đóng. */
    public static synchronized Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("DB Connected");
        }
        return connection;
    }

    /** Đóng connection nếu đang mở. */
    public static synchronized void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) connection.close();
                System.out.println("DB Closed");
            } catch (SQLException e) {
                System.err.println("DB Close error: " + e.getMessage());
            } finally {
                connection = null;
            }
        }
    }
}
