package JBornPractice.DAO;

import JBornPractice.DAO.domain.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static JBornPractice.DAO.DaoFactory.getConnection;

public class AccountDaoImpl implements Dao<Account, Integer> {
    static AccountDaoImpl accountDaoImpl;

    public static AccountDaoImpl getAccountDaoImpl() {
        if (accountDaoImpl == null) {
            accountDaoImpl = new AccountDaoImpl();
        }
        return accountDaoImpl;
    }

    private AccountDaoImpl() {
    }

    @Override
    public Account findById(Integer id) throws SQLException {
        Account account = null;

        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("select * from account where id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                account = new Account();
                fillAccount(account, rs);
            }
        }
        return account;
    }

    @Override
    public List<Account> findByAll() throws SQLException {
        Account account;
        List<Account> accounts;

        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("select * from account");

            ResultSet rs = ps.executeQuery();

            accounts = new ArrayList<>();
            while (rs.next()) {
                account = new Account();
                fillAccount(account, rs);
                accounts.add(account);
            }
        }
        return accounts;
    }

    @Override
    public Account insert(Account account) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("insert into account(login, password, email) values (?, ?, ?)");
            ps.setString(1, account.getLogin());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getEmail());

            ps.execute();
        }
        return account;
    }

    @Override
    public Account update(Account account) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("update account set login = ?, password = ?, email = ? where id = ?");
            ps.setString(1, account.getLogin());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getEmail());
            ps.setInt(4, account.getId());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                fillAccount(account, rs);
            }
        }
        return account;
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("delete from account where id = ?");
            ps.setInt(1, id);

            ps.execute();
        }
        return false;
    }

    public void fillAccount(Account account, ResultSet rs) throws SQLException {
        account.setId(rs.getInt("id"));
        account.setLogin(rs.getString("login"));
        account.setPassword(rs.getString("password"));
        account.setEmail(rs.getString("email"));
    }
}
