package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;


import java.sql.SQLException;



public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Петя", "Петин", (byte) 20);
        userDao.saveUser("Вася", "Васин", (byte) 12);
        userDao.saveUser("Коля", "Колин", (byte) 25);
        userDao.saveUser("Женя", "Женин", (byte) 18);

        System.out.println(userDao.getAllUsers());
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
