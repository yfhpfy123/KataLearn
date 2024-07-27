package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        String sql = """
                CREATE TABLE new_schema.Users (
                  ID BIGINT NOT NULL AUTO_INCREMENT,
                  Name CHAR(45) NOT NULL,
                  LastName CHAR(45) NOT NULL,
                  Age INT(3) NOT NULL,
                  PRIMARY KEY (ID))
                ENGINE = InnoDB
                DEFAULT CHARACTER SET = utf8;""";

        if (checkTable()) {
            System.out.println("Таблица уже существует");

        } else {
            try (Statement statement = Util.getConnect().createStatement()) {
                statement.execute(sql);
                System.out.println("Таблица создана");

            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE new_schema.Users";

        if (checkTable()) {
            try (Statement statement = Util.getConnect().createStatement()) {
                statement.execute(sql);
                System.out.println("Таблица удалена");

            } catch (SQLException e) {
                System.out.println("Таблицы не существует");

            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO new_schema.Users(name, lastName, age) VALUES (?, ?, ?)";

        if (checkTable()) {
            try (PreparedStatement pstm = Util.getConnect().prepareStatement(sql)) {
                pstm.setString(1, name);
                pstm.setString(2, lastName);
                pstm.setByte(3, age);

                pstm.executeUpdate();
                System.out.printf("User с именем - %s добавлен в базу данных\n", name);

            } catch (SQLException e) {
                e.printStackTrace();

            }

        } else {
            System.out.println("Сначала создайте таблицу!");

        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM new_schema.Users WHERE Id = ?";

        if (checkTable()) {
            try (PreparedStatement pstm = Util.getConnect().prepareStatement(sql)) {
                pstm.setLong(1, id);
                pstm.executeUpdate();
                System.out.printf("Пользователь с ID = %d удален\n", id);

            } catch (SQLException e) {
                e.printStackTrace();

            }
        } else {
            System.out.println("Сначала создайте таблицу!");

        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM new_schema.Users";

        if (checkTable()) {
            try (PreparedStatement pstm = Util.getConnect().prepareStatement(sql)) {
                ResultSet rs = pstm.executeQuery();

                while (rs.next()) {
                    userList.add(new User(rs.getString("Name"),
                            rs.getString("LastName"), (byte) rs.getInt("Age")));

                }

                if (userList.isEmpty()) {
                    return null;

                }

                return userList;

            } catch (SQLException e) {
                e.printStackTrace();
                return null;

            }

        } else {
            System.out.println("Сначала создайте таблицу!");
            return null;

        }
    }

    @Override
    public void cleanUsersTable() {
        String sql = "DELETE FROM new_schema.Users";

        if (checkTable()) {
            try (Statement statement = Util.getConnect().createStatement()) {
                statement.executeUpdate(sql);
                System.out.println("Все пользователи удалены");

            } catch (SQLException e) {
                e.printStackTrace();

            }
        } else {
            System.out.println("Сначала создайте таблицу!");

        }
    }

    @Override
    public boolean checkTable() {
        String sqlCheck = "SELECT EXISTS(SELECT * FROM new_schema.Users) AS table_exists;";

        try (Statement statement = Util.getConnect().createStatement()) {
            statement.execute(sqlCheck);
            return true;

        } catch (SQLException e) {
            return false;

        }
    }
}
