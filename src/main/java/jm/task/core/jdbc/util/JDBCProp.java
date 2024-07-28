package jm.task.core.jdbc.util;

public class JDBCProp {
    private static final String URL = "jdbc:mysql://localhost:3306/new_schema";
    private static final String USER = "root";
    private static final String PSWD = "root";

    public static String getURL() {
        return URL;
    }
    public static String getUSER() {
        return USER;
    }
    public static String getPSWD() {
        return PSWD;
    }
}
