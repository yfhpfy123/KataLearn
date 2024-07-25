package jm.task.core.jdbc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
//    private static String URL = "jdbc:mysql://localhost:3306/new_schema";
//    private static String USER = "root";
//    private static String PSWD = "root";

    public static Connection getConnect() throws SQLException {
        Connection connection;
        Properties properties = new Properties(); // Возможность менять базу данных
        String url = null;
        String login = null;
        String pswd = null;


//      Читаем properties и устанавливаем соединение
        try (InputStream input = new FileInputStream("src/main/java/jm/task/core/jdbc/util/sql.properties")){
            properties.load(input);

            url = properties.getProperty("database.url");
            login = properties.getProperty("database.login");
            pswd = properties.getProperty("database.pass");

            connection = DriverManager.getConnection(url, login, pswd);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
