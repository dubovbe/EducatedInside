package Practice;

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        regOrAuth();
    }

    public static void regOrAuth() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - Log in | 2 - Sign up");
        switch (scanner.nextInt()) {
            case 1:
                authorizationAccount();
                break;
            case 2:
                registrationNewAccount();
                break;
            default:
                System.out.println("Incorrect command");
        }
    }

    public static void registrationNewAccount() throws SQLException {
        boolean successfulRegistration = false;
        Scanner scanner = new Scanner(System.in);

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "admin");

        while (!successfulRegistration) {
            System.out.println("Enter new login: ");
            String login = scanner.nextLine();
            PreparedStatement loginCheckStatement = connection.prepareStatement("select count(id)\n" +
                    "from account\n" +
                    "where login = ?;");
            loginCheckStatement.setString(1, login);
            loginCheckStatement.executeQuery();
            ResultSet resultSet = loginCheckStatement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getInt("count") == 0) {
                    successfulRegistration = true;
                } else {
                    System.out.println("A user with this login already exists, try again");
                }
            }
            if (!successfulRegistration) continue;

            System.out.println("Enter new password: ");
            String password = DigestUtils.md5Hex(scanner.nextLine());
            System.out.println("Enter your email: ");
            String email = scanner.nextLine();

            PreparedStatement statement = connection.prepareStatement("insert into account(login, password, email) values (?, ?, ?);");
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.execute();
        }

        connection.close();
    }

    public static void authorizationAccount() throws SQLException {
        boolean successfulAuthorization = false;
        Scanner scanner = new Scanner(System.in);

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "admin");

        while (!successfulAuthorization) {
            System.out.println("Enter your login: ");
            String login = scanner.nextLine();
            System.out.println("Enter your password: ");
            String password = DigestUtils.md5Hex(scanner.nextLine());
            PreparedStatement statement = connection.prepareStatement("select count(id)\n" +
                    "from account\n" +
                    "where login = ? and password = ?;");
            statement.setString(1, login);
            statement.setString(2, password);
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getInt("count") > 0) {
                    System.out.println("Successful authorization");
                    successfulAuthorization = true;
                } else {
                    System.out.println("Invalid login or password, try again");
                }
            }
        }
        connection.close();
    }
}
