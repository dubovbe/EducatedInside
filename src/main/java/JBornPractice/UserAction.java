package JBornPractice;

import java.sql.*;
import java.util.Scanner;

public class UserAction {
    private String userLogin;
    private boolean isUserValidated;

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public boolean isUserValidated() {
        return isUserValidated;
    }

    public void setUserValidated(boolean userValidated) {
        isUserValidated = userValidated;
    }

    public UserAction(String userLogin, boolean isUserValidated) {
        this.userLogin = userLogin;
        this.isUserValidated = isUserValidated;
        if(!isUserValidated) {
            System.out.println("You need Log in / Sign up first");
        }
    }

    public void actionMenu() throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Action menu\n" +
                "1 - Show list of bank accounts | ???");
        switch (scanner.nextInt()) {
            case 1:
                showListOfBankAccounts();
                break;
            default:
                System.out.println("Incorrect command");
        }
    }

    public void showListOfBankAccounts() throws SQLException {

        if (isUserValidated()) {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "admin");

            PreparedStatement listOfBankAccountsStatement = connection.prepareStatement("select login, email, balance, currency, date\n" +
                    "from account\n" +
                    "inner join bank_account ba on account.id = ba.account_id\n" +
                    "where login = ?;");
            listOfBankAccountsStatement.setString(1, getUserLogin());
            ResultSet resultSet = listOfBankAccountsStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getString("login") + "\t" +
                                resultSet.getString("email") + "\t" +
                                resultSet.getInt("balance") + "\t" +
                                resultSet.getString("currency") + "\t" +
                                resultSet.getDate("date")
                );
            }
        }
    }
}
