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

    public void createUsersTable() throws SQLException {
        String sql = """
                CREATE TABLE new_schema.users (
                  Id BIGINT NOT NULL AUTO_INCREMENT,
                  Name CHAR(45) NOT NULL,
                  LastName CHAR(45) NOT NULL,
                  Age BINARY(1) NOT NULL,
                  PRIMARY KEY (Id))
                ENGINE = InnoDB
                DEFAULT CHARACTER SET = utf8;""";
        try (Statement statement = Util.getConnect().createStatement()) {
            statement.execute(sql);
            System.out.println("User table created");
        } catch (SQLException e) {
            dropUsersTable();
            createUsersTable();
        }
    }

    public void dropUsersTable() throws SQLException {
        String sql = "DROP TABLE new_schema.users";
        try (Statement statement = Util.getConnect().createStatement()) {
            statement.execute(sql);
            System.out.println("User table dropped");
        } catch (SQLException e) {
            createUsersTable();
            dropUsersTable();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String sql = "INSERT INTO new_schema.users(name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement pstm = Util.getConnect().prepareStatement(sql)) {
            pstm.setString(1, name);
            pstm.setString(2, lastName);
            pstm.setByte(3, age);

            int insert = pstm.executeUpdate();
            System.out.printf("insert %d: %s %s - %d years\n", insert, name, lastName, age);
        }
    }

    public void removeUserById(long id) throws SQLException {
        String sql = "DELETE FROM new_schema.users WHERE Id = ?";
        try (PreparedStatement pstm = Util.getConnect().prepareStatement(sql)) {
            pstm.setLong(1, id);

            int remove = pstm.executeUpdate();
            System.out.printf("remove: %d users from id = %d\n", remove, id);
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM new_schema.users";
        try (PreparedStatement pstm = Util.getConnect().prepareStatement(sql)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                userList.add(new User(rs.getString("Name"), rs.getString("LastName"), (byte) rs.getInt("Age")));
            }
            return userList;
        }
    }

    public void cleanUsersTable() throws SQLException {
        String sql = "DELETE FROM new_schema.users";
        try (Statement statement = Util.getConnect().createStatement()) {
            int delete = statement.executeUpdate(sql);
            System.out.printf("delete: %d users\n", delete);
        }
    }
}
