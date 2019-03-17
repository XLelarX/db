package lelar.company;

import java.sql.*;

class Connector {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/mine";
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "538320";

    private static Connection connection = null;

    static void initConnection() {
        try {
            connection =  DriverManager.getConnection(DATABASE_URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static Statement getStatement() {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
