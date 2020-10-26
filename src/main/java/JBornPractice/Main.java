package JBornPractice;

import JBornPractice.DAO.AccountDaoImpl;
import JBornPractice.DAO.domain.Account;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        AccountDaoImpl accountDaoImpl = AccountDaoImpl.getAccountDaoImpl();

        /*Account accountDaoById = accountDaoImpl.findById(1);
        System.out.println(accountDaoById);

        List<Account> accountsDaoFindByAll = accountDaoImpl.findByAll();
        for (Account a: accountsDaoFindByAll) {
            System.out.println(a);
        }*/

        /*Account accountDaoInsert = new Account();
        accountDaoInsert.setLogin("MainInsertTest");
        accountDaoInsert.setPassword(DigestUtils.md5Hex("MainInsertTest"));
        accountDaoInsert.setEmail("MainInsertTest@MainInsertTest.ru");
        accountDaoImpl.insert(accountDaoInsert);*/

        /*accountDaoImpl.delete(22);*/
    }
}
