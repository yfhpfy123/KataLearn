package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao jdbc = new UserDaoJDBCImpl();
    @Override
    public void createUsersTable() {
        jdbc.createUsersTable();
    }
    @Override
    public void dropUsersTable() {
        jdbc.dropUsersTable();
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        jdbc.saveUser(name, lastName, age);
    }
    @Override
    public void removeUserById(long id) {
        jdbc.removeUserById(id);
    }
    @Override
    public List<User> getAllUsers() {
        return jdbc.getAllUsers();
    }
    @Override
    public void cleanUsersTable() {
        jdbc.cleanUsersTable();
    }
}
