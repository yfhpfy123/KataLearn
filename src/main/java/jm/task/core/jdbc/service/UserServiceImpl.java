package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl jdbc = new UserDaoJDBCImpl();

    public void createUsersTable() throws SQLException {
        jdbc.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        jdbc.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        jdbc.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws SQLException {
        jdbc.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return jdbc.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        jdbc.cleanUsersTable();
    }
}
