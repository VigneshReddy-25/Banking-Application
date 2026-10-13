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

    public boolean addTransaction(Transaction transaction) throws DatabaseOperationException {

    	String query = "INSERT INTO transactions "
    	        + "(account_number, transaction_type, amount, balance_after_transaction) "
    	        + "VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setLong(1, transaction.getAccountNumber());
            ps.setString(2, transaction.getTransactionType());
            ps.setDouble(3, transaction.getAmount());
            ps.setDouble(4, transaction.getBalanceAfterTransaction());

            int rows = ps.executeUpdate();

            return rows > 0;
        }
        catch (SQLException e) {
            throw new DatabaseOperationException("Failed to create transaction in database.", e);
        }
    }
    
    public boolean removeTransaction(long accountNumber) throws DatabaseOperationException {

        String query = "DELETE FROM transactions WHERE account_number = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setLong(1, accountNumber);
            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to remove transactions from database.", e);
        }
    }
    
    public List<Transaction> getTransactionsByAccount(long accountNumber) throws DatabaseOperationException {

        List<Transaction> transactions = new ArrayList<>();

        String query = "SELECT transaction_id, account_number, transaction_type, "
                + "amount, balance_after_transaction "
                + "FROM transactions WHERE account_number = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setLong(1, accountNumber);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    Transaction transaction = new Transaction(
                            rs.getLong("account_number"),
                            rs.getString("transaction_type"),
                            rs.getDouble("amount"),
                            rs.getDouble("balance_after_transaction"));

                    transaction.setTransactionId(
                            rs.getLong("transaction_id"));

                    transactions.add(transaction);
                }
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException(
                    "Failed to retrieve account transactions from database.", e);
        }

        return transactions;
    }
    
    public List<Transaction> getAllTransactions() throws DatabaseOperationException {

        List<Transaction> transactions = new ArrayList<>();

        String query = "SELECT transaction_id, account_number, transaction_type, "
                + "amount, balance_after_transaction FROM transactions";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Transaction transaction = new Transaction(
                        rs.getLong("account_number"),
                        rs.getString("transaction_type"),
                        rs.getDouble("amount"),
                        rs.getDouble("balance_after_transaction"));

                transaction.setTransactionId(
                        rs.getLong("transaction_id"));

                transactions.add(transaction);
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException(
                    "Failed to retrieve all transactions from database.", e);
        }

        return transactions;
    }
}