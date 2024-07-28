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
        Session ss = Util.getSession();
        try {
            ss.beginTransaction();

            ss.createSQLQuery("""
                CREATE TABLE IF NOT EXISTS new_schema.Users (
                  ID BIGINT NOT NULL AUTO_INCREMENT,
                  Name CHAR(45) NOT NULL,
                  LastName CHAR(45) NOT NULL,
                  Age INT(3) NOT NULL,
                  PRIMARY KEY (ID))
                ENGINE = InnoDB
                DEFAULT CHARACTER SET = utf8;""").executeUpdate();

            ss.getTransaction().commit();
            System.out.println("Таблица создана");
        } finally {
            ss.close();
        }
    }

    @Override
    public void dropUsersTable() {
        if (checkTable()) {
            Session ss = Util.getSession();
            try {
                ss.beginTransaction();

                ss.createSQLQuery("DROP TABLE new_schema.Users").executeUpdate();

                ss.getTransaction().commit();
                System.out.println("Таблица удалена");
            } finally {
                ss.close();
            }
        } else {
            System.out.println("Таблица еще не создавалась");
        }
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
                System.out.println("Все пользователи удалены.");
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
