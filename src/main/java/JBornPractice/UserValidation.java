package JBornPractice;

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.Scanner;

public class UserValidation {
    private boolean isUserValidated = false;
    private String userLogin;
    private String userPassword;
    private String userEmail;

    public boolean isUserValidated() {
        return isUserValidated;
    }

    public void setUserValidated(boolean userValidated) {
        isUserValidated = userValidated;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userName) {
        this.userLogin = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void menu() throws SQLException {
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

    public void registrationNewAccount() throws SQLException {
        boolean successfulRegistration = false;
        Scanner scanner = new Scanner(System.in);

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "admin");

        while (!successfulRegistration) {
            System.out.println("Enter new login: ");
            setUserLogin(scanner.nextLine());
            PreparedStatement loginCheckStatement = connection.prepareStatement("select count(id)\n" +
                    "from account\n" +
                    "where login = ?;");
            loginCheckStatement.setString(1, getUserLogin());
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
            setUserPassword(DigestUtils.md5Hex(scanner.nextLine()));
            System.out.println("Enter your email: ");
            setUserEmail(scanner.nextLine());

            PreparedStatement userRegistrationStatement = connection.prepareStatement("insert into account(login, password, email) " +
                    "values (?, ?, ?);");
            userRegistrationStatement.setString(1, getUserLogin());
            userRegistrationStatement.setString(2, getUserPassword());
            userRegistrationStatement.setString(3, getUserEmail());
            userRegistrationStatement.execute();
            System.out.println("Successful registration");
        }

        connection.close();
    }

    public void authorizationAccount() throws SQLException {
        boolean successfulAuthorization = false;
        Scanner scanner = new Scanner(System.in);

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "admin");

        while (!successfulAuthorization) {
            System.out.println("Enter your login: ");
            setUserLogin(scanner.nextLine());
            System.out.println("Enter your password: ");
            setUserPassword(DigestUtils.md5Hex(scanner.nextLine()));
            PreparedStatement userAuthorizationStatement = connection.prepareStatement("select count(id)\n" +
                    "from account\n" +
                    "where login = ? and password = ?;");
            userAuthorizationStatement.setString(1, getUserLogin());
            userAuthorizationStatement.setString(2, getUserPassword());
            userAuthorizationStatement.executeQuery();
            ResultSet resultSet = userAuthorizationStatement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getInt("count") > 0) {
                    System.out.println("Successful authorization");
                    successfulAuthorization = true;
                } else {
                    System.out.println("Invalid login or password, try again");
                }
            }
        }
        setUserValidated(successfulAuthorization);
        connection.close();
    }
}
