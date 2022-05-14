package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static Connection connection = null;
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                Statement statement = connection.createStatement();
            }
            System.out.println("Соединение установлено");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return connection;
    }



}
