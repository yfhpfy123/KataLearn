package jm.task.core.jdbc.util;

//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.*;
//import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String url = "jdbc:mysql://localhost:3306/new_schema";
    private static String user = "root";
    private static String pswd = "root";
//    private static String url = null;
//    private static String user = null;
//    private static String pswd = null;

    public static Session getSession() {
        Configuration config = new Configuration().addAnnotatedClass(User.class);

        SessionFactory ssf = config.buildSessionFactory();
        return ssf.getCurrentSession();
    }

    public static Connection getConnect() {
        Connection connection;
//        Properties properties = new Properties(); // Возможность менять базу данных

//      Читаем properties и устанавливаем соединение
        try {
            connection = DriverManager.getConnection(url, user, pswd);
//            InputStream input = new FileInputStream("src/main/java/jm/task/core/jdbc/util/Hibernate.properties");
//            properties.load(input);

//            url = properties.getProperty("hibernate.connection.url");
//            user = properties.getProperty("hibernate.connection.username");
//            pswd = properties.getProperty("hibernate.connection.password");

            return connection;
        } catch (SQLException e) {
            System.out.println("Не удалось установить подключение к базе данных");
            return null;
        }
    }
}
