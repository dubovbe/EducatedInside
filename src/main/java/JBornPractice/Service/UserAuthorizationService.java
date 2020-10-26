package JBornPractice.Service;

import java.sql.SQLException;
import java.util.Scanner;

public class UserAuthorizationService {
/*    private String userId;
    private String userLogin;
    private String userPassword;
    private String userEmail;
    private boolean isUserAuthorized = false;
    DatabaseQuery databaseQuery;

    public void userAuthorizationByLogin(String login, String password) throws SQLException {
        String userId = databaseQuery.userAuthorizationQuery(login, password);
        while (!isUserAuthorized) {
            if(userId != null) {
                System.out.println(String.format("Successful user authorization. User \"%s\" (id %s).", login, userId));
                this.userId = userId;
                this.userLogin = login;
                this.isUserAuthorized = true;
            } else {
                System.out.println(String.format("Authorisation Error. User \"%s\" not found or password is incorrect.", login));
            }
        }
    }

    public void userAuthorizationByEmail(String email, String password) {

    }

    public void userLogout (boolean isUserAuthorized) {
        if (isUserAuthorized) {
            System.out.println(String.format("You are logged in as %s (id %s). Are you sure you want to log out?\n(Enter Y/N)", userLogin, userId));
            Scanner s = new Scanner(System.in);
            if (s.nextLine().equals("Y")) {
                this.isUserAuthorized = false;
                System.out.println("You are logged out");
            }
        } else {
            System.out.println("You have not logged in yet");
        }
    }*/
}
