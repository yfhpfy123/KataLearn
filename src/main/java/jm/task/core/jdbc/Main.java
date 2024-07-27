package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDao userDao = new UserDaoJDBCImpl();
        UserDao userDaoH = new UserDaoHibernateImpl();
        System.out.println("--------------------JDBC-------------------");

        // JDBC блок
        userDao.createUsersTable();
        System.out.println();

        userDao.saveUser("Петя", "Петин", (byte) 20);
        userDao.saveUser("Вася", "Васин", (byte) 12);
        userDao.saveUser("Коля", "Колин", (byte) 25);
        userDao.saveUser("Женя", "Женин", (byte) 18);
        System.out.println();

        userDao.removeUserById(2);
        System.out.println(userDao.getAllUsers());
        System.out.println();

        userDao.cleanUsersTable();
        userDao.dropUsersTable();
        System.out.println("\n-------------------Hibernate-------------------");

        // Hibernate блок
        userDaoH.createUsersTable();
        System.out.println();

        userDaoH.saveUser("Hib_Петя", "Петин", (byte) 20);
        userDaoH.saveUser("Hib_Вася", "Васин", (byte) 12);
        userDaoH.saveUser("Hib_Коля", "Колин", (byte) 25);
        userDaoH.saveUser("Hib_Женя", "Женин", (byte) 18);
        System.out.println();

        userDaoH.removeUserById(2);
        System.out.println(userDaoH.getAllUsers());
        System.out.println();

        userDaoH.cleanUsersTable();
        userDaoH.dropUsersTable();

    }
}
