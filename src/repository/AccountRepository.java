package repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Exception.DatabaseOperationException;
import model.Account;
import util.DatabaseConnection;
public class AccountRepository {
	
	public boolean addAccount(Account account) throws DatabaseOperationException {

	    String query = "INSERT INTO account " + "(account_number, account_holder_name, balance, account_type) " + "VALUES (?, ?, ?, ?)";

	    try (Connection con = DatabaseConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {

	        ps.setLong(1, account.getAccountNumber());
	        ps.setString(2, account.getAccountHolderName());
	        ps.setDouble(3, account.getBalance());
	        ps.setString(4, "NORMAL");

	        int rows = ps.executeUpdate();

	        return rows > 0;
	    }
	    catch (SQLException e) {
	        throw new DatabaseOperationException("Failed to create account in database.", e);
	    }
	}

	public Account findAccount(long accountNumber) throws DatabaseOperationException {

	    String query = "SELECT * FROM account WHERE account_number = ?";
	
	    try (Connection con = DatabaseConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {
	
	        ps.setLong(1, accountNumber);
	
	        try (ResultSet rs = ps.executeQuery()) {
	
	            if (rs.next()) {
	
	                long accountNo = rs.getLong("account_number");
	                String accountHolderName = rs.getString("account_holder_name");
	                double balance = rs.getDouble("balance");
	
	                Account account = new Account(accountNo, accountHolderName);
	
	                account.setBalance(balance);
	
	                return account;
	            }
	        }
	    }
	    catch (SQLException e) {
	        throw new DatabaseOperationException("Failed to find account in database.", e);
	    }

	    return null;
	}
	
	public boolean removeAccount(long accountNumber) throws DatabaseOperationException {
		String query="delete from account where account_number= ? ";
		
		try(Connection con=DatabaseConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(query)){
			
			ps.setLong(1, accountNumber);
			int rows = ps.executeUpdate();
			return rows>0;
			
		}
		catch (SQLException e) {
	        throw new DatabaseOperationException("Failed to remove account in database.", e);
	    }
	}
	
	public boolean updateBalance(long accountNumber, double balance) throws DatabaseOperationException {

	    String query = "UPDATE account SET balance = ? WHERE account_number = ?";

	    try (Connection con = DatabaseConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {

	        ps.setDouble(1, balance);
	        ps.setLong(2, accountNumber);

	        int rows = ps.executeUpdate();

	        return rows > 0;
	    }
	    catch (SQLException e) {
	        throw new DatabaseOperationException("Failed to update account balance in database.", e);
	    }
	}
	
	public List<Account> getAllAccounts() throws DatabaseOperationException {

	    List<Account> accounts = new ArrayList<>();

	    String query = "SELECT * FROM account";

	    try (Connection con = DatabaseConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {

	            long accountNumber = rs.getLong("account_number");
	            String accountHolderName =
	                    rs.getString("account_holder_name");
	            double balance = rs.getDouble("balance");

	            Account account =
	                    new Account(accountNumber, accountHolderName);

	            account.setBalance(balance);

	            accounts.add(account);
	        }
	    }
	    catch (SQLException e) {
	        throw new DatabaseOperationException("Failed to retreive account in database.", e);
	    }

	    return accounts;
	}
}
