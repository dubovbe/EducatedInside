package JBornPractice.DAO;

import JBornPractice.DAO.domain.Account;
import JBornPractice.DAO.domain.BankAccount;
import JBornPractice.DAO.domain.BankTransaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static JBornPractice.DAO.DaoFactory.getConnection;

public class BankTransactionDaoImpl implements Dao<BankTransaction, Integer> {
    static BankTransactionDaoImpl bankTransactionDaoImpl;

    public static BankTransactionDaoImpl getBankTransactionDaoImpll() {
        if (bankTransactionDaoImpl == null) {
            bankTransactionDaoImpl = new BankTransactionDaoImpl();
        }
        return bankTransactionDaoImpl;
    }

    private BankTransactionDaoImpl() {
    }

    @Override
    public BankTransaction findById(Integer id) throws SQLException {
        BankTransaction bankTransaction = null;

        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("select * from bank_transaction where id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                bankTransaction = new BankTransaction();
                fillBankTransaction(bankTransaction, rs);
            }
        }
        return bankTransaction;
    }

    private List<BankTransaction> findAllByAccountId(Integer accountId) throws SQLException {
        BankTransaction bankTransaction;
        List<BankTransaction> bankTransactions;
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("select * from bank_transaction where account_id = ?");
            ps.setInt(1, accountId);

            ResultSet rs = ps.executeQuery();

            bankTransactions = new ArrayList<>();
            while (rs.next()) {
                bankTransaction = new BankTransaction();
                fillBankTransaction(bankTransaction, rs);
                bankTransactions.add(bankTransaction);
            }
        }
        return bankTransactions;
    }

    @Override
    public List<BankTransaction> findByAll() throws SQLException {
        return null;
    }

    @Override
    public BankTransaction insert(BankTransaction bankTransaction) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("insert into bank_transaction(bank_account_id, category, amount) values (?, ?, ?)");
            ps.setInt(1, bankTransaction.getBankAccountId());
            ps.setString(2, bankTransaction.getCategory());
            ps.setInt(3, bankTransaction.getAmount());
            ps.execute();
        }
        return bankTransaction;
    }

    @Override
    public BankTransaction update(BankTransaction bankTransaction) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("update bank_transaction set bank_account_id = ?, category = ?, amount = ? where id = ?");
            ps.setInt(1, bankTransaction.getBankAccountId());
            ps.setString(2, bankTransaction.getCategory());
            ps.setInt(3, bankTransaction.getAmount());
            ps.setInt(4, bankTransaction.getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                bankTransaction = new BankTransaction();
                fillBankTransaction(bankTransaction, rs);
            }
        }
        return bankTransaction;
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("delete from bank_transaction where id = ?");
            ps.setInt(1, id);
            ps.execute();
        }
        return false;
    }

    public void fillBankTransaction(BankTransaction bankTransaction, ResultSet rs) throws SQLException {
        bankTransaction.setId(rs.getInt("id"));
        bankTransaction.setBankAccountId(rs.getInt("bank_account_id"));
        bankTransaction.setCategory(rs.getString("category"));
        bankTransaction.setAmount(rs.getInt("amount"));
    }
}
