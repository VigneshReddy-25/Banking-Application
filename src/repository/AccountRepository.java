package repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import model.Account;
import util.DatabaseConnection;
public class AccountRepository {

	private ArrayList<Account> accounts=new ArrayList<>();
	
	public boolean addAccount(Account account) throws SQLException {

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
	}

	public Account findAccount(long accountNumber) throws SQLException {

	    String query = "SELECT * FROM account WHERE account_number = ?";
	
	    try (Connection con = DatabaseConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {
	
	        ps.setLong(1, accountNumber);
	
	        try (ResultSet rs = ps.executeQuery()) {
	
	            if (rs.next()) {
	
	                long accountNo = rs.getLong("account_number");
	                String accountHolderName =
	                        rs.getString("account_holder_name");
	                double balance = rs.getDouble("balance");
	
	                Account account =
	                        new Account(accountNo, accountHolderName);
	
	                account.setBalance(balance);
	
	                return account;
	            }
	        }
	    }

	    return null;
	}
	
	public boolean removeAccount(long accountNumber) throws SQLException {
		String query="delete from account where account_number= ? ";
		try(Connection con=DatabaseConnection.getConnection();
				PreparedStatement ps=con.prepareStatement(query)){
			ps.setLong(1, accountNumber);
			int rows = ps.executeUpdate();
			return rows>0;
			
		}
	}
	public List<Account> getAllAccounts(){
		String query="select * from account";
		
		return new ArrayList<>(accounts);
	}
}
