package JBornPractice.DAO;

import JBornPractice.DAO.domain.Account;
import JBornPractice.DAO.domain.BankAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static JBornPractice.DAO.DaoFactory.getConnection;

public class BankAccountDaoImpl implements Dao<BankAccount, Integer> {
    static BankAccountDaoImpl bankAccountDaoImpl;

    public static BankAccountDaoImpl getBankAccountDaoImpl() {
        if (bankAccountDaoImpl == null) {
            bankAccountDaoImpl = new BankAccountDaoImpl();
        }
        return bankAccountDaoImpl;
    }

    private BankAccountDaoImpl() {
    }

    @Override
    public BankAccount findById(Integer id) throws SQLException {
        BankAccount bankAccount = null;

        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("select * from bank_account where id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                bankAccount = new BankAccount();
                fillBankAccount(bankAccount, rs);
            }
        }
        return bankAccount;
    }

    public List<BankAccount> findAllByAccountId(Integer accountId) throws SQLException {
        BankAccount bankAccount;
        List<BankAccount> bankAccounts;
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("select * from bank_account where account_id = ?");
            ps.setInt(1, accountId);

            ResultSet rs = ps.executeQuery();

            bankAccounts = new ArrayList<>();
            while (rs.next()) {
                bankAccount = new BankAccount();
                fillBankAccount(bankAccount, rs);
                bankAccounts.add(bankAccount);
            }
        }
        return bankAccounts;
    }

    @Override
    public List<BankAccount> findByAll() throws SQLException {
        return null;
    }

    @Override
    public BankAccount insert(BankAccount bankAccount) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("insert into bank_account(account_id, balance, currency, date) values (?, ?, ?, ?)");
            ps.setInt(1, bankAccount.getAccountId());
            ps.setInt(2, bankAccount.getBalance());
            ps.setString(3, bankAccount.getCurrency());
            ps.setDate(4, (Date) bankAccount.getDate());
            ps.execute();
        }
        return bankAccount;
    }

    @Override
    public BankAccount update(BankAccount bankAccount) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("update bank_account set account_id = ?, balance = ?, currency = ?, date = ? where id = ?");
            ps.setInt(1, bankAccount.getAccountId());
            ps.setInt(2, bankAccount.getBalance());
            ps.setString(3, bankAccount.getCurrency());
            ps.setDate(4, (Date) bankAccount.getDate());
            ps.setInt(5, bankAccount.getId());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                fillBankAccount(bankAccount, rs);
            }
        }
        return bankAccount;
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("delete from bank_account where id = ?");
            ps.setInt(1, id);
            ps.execute();
        }
        return false;
    }

    public void fillBankAccount(BankAccount bankAccount, ResultSet rs) throws SQLException {
        bankAccount.setId(rs.getInt("id"));
        bankAccount.setAccountId(rs.getInt("account_id"));
        bankAccount.setBalance(rs.getInt("balance"));
        bankAccount.setCurrency(rs.getString("currency"));
        bankAccount.setDate(rs.getDate("date"));
    }
}
