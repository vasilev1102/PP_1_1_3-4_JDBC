package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS mydbtest.users (id BIGINT NOT NULL auto_increment,"+
                "name VARCHAR(30) NOT NULL,lastName VARCHAR(30) NOT NULL,"+
                "age TINYINT,PRIMARY KEY(id));";
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(createTable);
            System.out.println("Таблица users создана");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String dropTable = "DROP TABLE IF EXISTS mydbtest.users";
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(dropTable);
            System.out.println("Таблица users удалена");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String preparedSaveUser = "INSERT INTO mydbtest.users(name,lastName,age) VALUES(?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(preparedSaveUser)) {
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
            System.out.println("user " + name + " внесен в таблицу");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;
    }

    public void removeUserById(long id) {
        String removeUser = "DELETE FROM mydbtest.users WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(removeUser)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String getUsers = "SELECT * FROM mydbtest.users";
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(getUsers);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        String cleanTable = "DELETE FROM mydbtest.users";
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(cleanTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
