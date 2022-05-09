package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static Connection connection = null;
    private static Statement statement = null;
    private static final String userName = "root";
    private static final String password = "root";
    private static final String url = "jdbc:mysql://localhost:3306";
    private static final String driver = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
        try {
            Class.forName(driver);
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, userName, password);
                Statement statement = connection.createStatement();
            }
            System.out.println("Соединение установлено");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }



}
