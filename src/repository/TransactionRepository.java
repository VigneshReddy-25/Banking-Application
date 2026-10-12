package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Exception.DatabaseOperationException;
import model.Transaction;
import util.DatabaseConnection;

public class TransactionRepository {

    public boolean addTransaction(Transaction transaction)
            throws DatabaseOperationException {

        String query = "INSERT INTO transactions VALUES ( ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setLong(2, transaction.getAccountNumber());
            ps.setString(3, transaction.getTransactionType());
            ps.setDouble(4, transaction.getAmount());
            ps.setDouble(5, transaction.getBalanceAfterTransaction());

            int rows = ps.executeUpdate();

            return rows > 0;
        }
        catch (SQLException e) {
            throw new DatabaseOperationException("Failed to create transaction in database.", e);
        }
    }

    public List<Transaction> getTransactionsByAccount(long accountNumber) throws DatabaseOperationException {

        List<Transaction> transactions = new ArrayList<>();

        String query = "SELECT * FROM transactions WHERE account_number = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setLong(1, accountNumber);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Transaction trans = new Transaction(rs.getLong(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5));
                    trans.setTransactionId(rs.getLong(1));
                    transactions.add(trans);
                }
            }
        }
        catch (SQLException e) {
            throw new DatabaseOperationException("Failed to retrieve account transactions from database.", e);
        }

        return transactions;
    }

    public List<Transaction> getAllTransactions() throws DatabaseOperationException {

        String query = "SELECT * FROM transactions";

        List<Transaction> transactions = new ArrayList<>();

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Transaction trans = new Transaction(rs.getLong(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5));
                trans.setTransactionId(1);
                transactions.add(trans);
            }
        }
        catch (SQLException e) {
            throw new DatabaseOperationException("Failed to retrieve all transactions from database.", e);
        }

        return transactions;
    }
}