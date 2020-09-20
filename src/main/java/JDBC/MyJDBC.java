package JDBC;

import java.sql.*;

public class MyJDBC {
    public static void main(String[] args) throws SQLException {
        int userID = 2;
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "admin");

        PreparedStatement statement = connection.prepareStatement("select id, login, email\n" +
                "from account\n" +
                "where id = ?;");
        statement.setInt(1, userID);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            System.out.println(
                    resultSet.getInt("id") + "\t" +
                            resultSet.getString("login") + "\t" +
                            resultSet.getString("email")
            );
        }
    }
}
