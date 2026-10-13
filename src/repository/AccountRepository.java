package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Exception.DatabaseOperationException;
import model.Account;
import model.CurrentAccount;
import model.SavingsAccount;
import util.DatabaseConnection;

public class AccountRepository {
	
	public boolean addAccount(Account account) throws DatabaseOperationException {

	    String query = "INSERT INTO account "
	            + "(account_holder_name, balance, account_type, interest_rate, overdraft_limit) "
	            + "VALUES (?, ?, ?, ?, ?)";
	
	    String accountType;
	    double interestRate = 0;
	    double overdraftLimit = 0;
	
	    if (account instanceof SavingsAccount) {
	        accountType = "SAVINGS";
	        interestRate = ((SavingsAccount) account).getInterestRate();
	    } else if (account instanceof CurrentAccount) {
	        accountType = "CURRENT";
	        overdraftLimit = ((CurrentAccount) account).getOverdraftLimit();
	    } else {
	        accountType = "NORMAL";
	    }
	
	    try (Connection con = DatabaseConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
	
	        ps.setString(1, account.getAccountHolderName());
	        ps.setDouble(2, account.getBalance());
	        ps.setString(3, accountType);
	        ps.setDouble(4, interestRate);
	        ps.setDouble(5, overdraftLimit);
	
	        int rows = ps.executeUpdate();
	
	        if (rows > 0) {
	            try (ResultSet rs = ps.getGeneratedKeys()) {
	                if (rs.next()) {
	                    account.setAccountNumber(rs.getLong(1));
	                    return true;
	                }
	            }
	        }
	
	        return false;
	
	    } catch (SQLException e) {
	        throw new DatabaseOperationException(
	                "Failed to create account in database.", e);
	    }
	}

	public Account findAccount(long accountNumber) throws DatabaseOperationException {

	    String query = "SELECT * FROM account WHERE account_number = ?";
	
	    try (Connection con = DatabaseConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {
	
	        ps.setLong(1, accountNumber);
	
	        try (ResultSet rs = ps.executeQuery()) {
	
	            if (rs.next()) {
	
	                String accountHolderName = rs.getString("account_holder_name");
	                double balance = rs.getDouble("balance");
	                String accountType= rs.getString("account_type");
	                double interestRate = rs.getDouble("interest_rate");
	                double overdraftLimit = rs.getDouble("overdraft_limit");
	                
	                if ("NORMAL".equals(accountType)) {
	                    Account account = new Account(accountNumber, accountHolderName);
	                    account.setBalance(balance);
	                    return account;

	                } else if ("SAVINGS".equals(accountType)) {
	                    SavingsAccount account =
	                            new SavingsAccount(accountNumber, accountHolderName, interestRate);
	                    account.setBalance(balance);
	                    return account;
	                } else if ("CURRENT".equals(accountType)) {
	                    CurrentAccount account =
	                            new CurrentAccount(accountNumber, accountHolderName, overdraftLimit);
	                    account.setBalance(balance);
	                    return account;

	                } else {
	                    throw new DatabaseOperationException(
	                            "Unknown account type in database: " + accountType, null);
	                }
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
	            String accountHolderName = rs.getString("account_holder_name");
	            double balance = rs.getDouble("balance");
	            String accountType= rs.getString("account_type");
                double interestRate = rs.getDouble("interest_rate");
                double overdraftLimit = rs.getDouble("overdraft_limit");
                
                
                if ("NORMAL".equals(accountType)) {
                    Account account = new Account(accountNumber, accountHolderName);
                    account.setBalance(balance);
                    accounts.add(account);

                } else if ("SAVINGS".equals(accountType)) {
                    SavingsAccount account =
                            new SavingsAccount(accountNumber, accountHolderName, interestRate);
                    account.setBalance(balance);
                    accounts.add(account);

                } else if ("CURRENT".equals(accountType)) {
                    CurrentAccount account =
                            new CurrentAccount(accountNumber, accountHolderName, overdraftLimit);
                    account.setBalance(balance);
                    accounts.add(account);

                } else {
                    throw new DatabaseOperationException(
                            "Unknown account type in database: " + accountType, null);
                }	            
	        }
	    }
	    catch (SQLException e) {
	        throw new DatabaseOperationException("Failed to retreive account in database.", e);
	    }

	    return accounts;
	}
}
