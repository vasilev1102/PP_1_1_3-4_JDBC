package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    private static UserService userService = new UserServiceImpl();
    public static void main(String[] args) throws ClassNotFoundException {
        // реализуйте алгоритм здесь
        userService.createUsersTable();
        userService.saveUser("vasa","pupkin" , (byte) 15);
        userService.saveUser("petya","vaskin" , (byte) 25);
        userService.saveUser("goga","pupkin" , (byte) 45);
        userService.saveUser("grisha","gogin" , (byte) 10);
        for (User user: userService.getAllUsers()) {
            System.out.println(user.toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
