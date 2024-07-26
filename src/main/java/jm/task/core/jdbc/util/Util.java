package jm.task.core.jdbc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String url = "jdbc:mysql://localhost:3306/new_schema";
    private static String user = "root";
    private static String pswd = "root";
//    private static String url = null;
//    private static String user = null;
//    private static String pswd = null;

    public static Connection getConnect() throws SQLException {
        Connection connection;
        Properties properties = new Properties(); // Возможность менять базу данных

//      Читаем properties и устанавливаем соединение
        try (InputStream input = new FileInputStream("src/main/java/jm/task/core/jdbc/util/sql.properties")){
            properties.load(input);

//            url = properties.getProperty("database.url");
//            user = properties.getProperty("database.login");
//            pswd = properties.getProperty("database.pass");

            connection = DriverManager.getConnection(url, user, pswd);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
