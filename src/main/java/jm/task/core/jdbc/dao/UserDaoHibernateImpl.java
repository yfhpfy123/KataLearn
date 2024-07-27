package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        UserDaoJDBCImpl create = new UserDaoJDBCImpl();
        create.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        UserDaoJDBCImpl drop = new UserDaoJDBCImpl();
        drop.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        if (checkTable()) {
            User user = new User(name, lastName, age);
            Session ss = Util.getSession();
            try {
                ss.beginTransaction();

                ss.save(user);

                ss.getTransaction().commit();
                System.out.printf("User с именем - %s добавлен в базу данных\n", name);
            } finally {
                ss.close();
            }
        } else {
            System.out.println("Сначла создайте таблицу");
        }
    }

    @Override
    public void removeUserById(long id) {
        if (checkTable()) {
            Session ss = Util.getSession();
            try {
                ss.beginTransaction();

                User user = ss.get(User.class, id);
                ss.delete(user);

                ss.getTransaction().commit();
                System.out.printf("Пользователь с ID = %d удален\n", id);
            } finally {
                ss.close();
            }
        } else {
            System.out.println("Сначла создайте таблицу");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList;
        if (checkTable()) {
            Session ss = Util.getSession();
            try {
                ss.beginTransaction();

                userList = ss.createQuery("FROM User").getResultList();

                ss.getTransaction().commit();
            } finally {
                ss.close();
            }
        } else {
            System.out.println("Сначала создайте таблицу");
            userList = null;
        }

        return userList;
    }

    @Override
    public void cleanUsersTable() {
        if (checkTable()) {
            Session ss = Util.getSession();
            try {
                ss.beginTransaction();

                ss.createQuery("DELETE FROM User");

                ss.getTransaction().commit();
                System.out.println("Все пользователи удалены");
            } finally {
                ss.close();
            }
        } else {
            System.out.println("Сначала создайте таблицу");
        }
    }

    @Override
    public boolean checkTable() {
        UserDaoJDBCImpl check = new UserDaoJDBCImpl();
        return check.checkTable();
    }
}
