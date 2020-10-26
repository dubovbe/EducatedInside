package JBornPractice.TerminalView;

import JBornPractice.Service.UserAuthorizationService;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;
import java.util.Scanner;

public class UserAuthorizationView {
/*    private String userLogin;
    private String userPassword;
    private String userEmail;
    UserAuthorizationService userAuthorizationService;

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
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

    public void userAuthorizationByLogin() throws SQLException {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter your login: ");
        setUserLogin(s.nextLine());
        System.out.println("Enter your password: ");
        setUserPassword(DigestUtils.md5Hex(s.nextLine()));
        userAuthorizationService.userAuthorizationByLogin(getUserLogin(), getUserPassword());
    }*/
}
