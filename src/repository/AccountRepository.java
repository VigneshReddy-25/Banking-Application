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
		
		
		String query="select * from account where account_number= ?";
		try(Connection con=DatabaseConnection.getConnection();
				PreparedStatement ps=con.prepareStatement(query)){
			
			ps.setLong(1,accountNumber);
			ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
	                // Assuming you map the row to an Account object here
//	                Account account = new Account();
//	                account.setAccountNumber(rs.getLong("account_number"));
//	                account.setAccountHolderName(rs.getString("account_holder_name"));
//	                account.setBalance(rs.getBigDecimal("balance"));
//	                account.setAccountType(rs.getString("account_type"));
//	                account.setInterestRate(rs.getBigDecimal("interest_rate"));
//	                return account;
	            }
		}
		
	}
	public boolean removeAccount(long accountNumber) {
		Iterator<Account> ite=accounts.iterator();
		while(ite.hasNext()) {
			Account account1=ite.next();
			if(account1.getAccountNumber()==accountNumber) {
				ite.remove();
				return true;
			}
		}
		return false;
	}
	public List<Account> getAllAccounts(){
		return new ArrayList<>(accounts);
	}
}
