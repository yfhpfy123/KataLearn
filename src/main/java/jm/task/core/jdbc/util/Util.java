package jm.task.core.jdbc.util;

//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
import com.mysql.cj.jdbc.ConnectionImpl;
import org.hibernate.Session;

import java.sql.*;


public class Util {
    // реализуйте настройку соеденения с БД

    public static Session getSession() {

        return HibernateProp.getSessionFactory().openSession();
    }

    public static Connection getConnect() {
//        Properties properties = new Properties(); // Возможность менять базу данных

//      Читаем properties и устанавливаем соединение
        try (Connection connection = DriverManager.getConnection(JDBCProp.getURL(), JDBCProp.getUSER(), JDBCProp.getPSWD())){

//            InputStream input = new FileInputStream("src/main/java/jm/task/core/jdbc/util/Hibernate.properties");
//            properties.load(input);

//            url = properties.getProperty("hibernate.connection.url");
//            user = properties.getProperty("hibernate.connection.username");
//            pswd = properties.getProperty("hibernate.connection.password");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось установить подключение к базе данных");
        }
    }
}
