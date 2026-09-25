package repository;
import java.util.*;
import model.Account;
public class AccountRepository {

	private ArrayList<Account> accounts=new ArrayList<>();
	
	public boolean addAccount(Account account) {
		Iterator<Account> ite=accounts.iterator();
		while(ite.hasNext()) {
			Account ac1=ite.next();
			if(ac1.getAccountNumber()==account.getAccountNumber()) {
				return false;
			}
		}
		accounts.add(account);
		return true;
	}

	public Account findAccount(long accountNumber) {
		Iterator<Account> ite=accounts.iterator();
		while(ite.hasNext()) {
			Account account1=ite.next();
			if(account1.getAccountNumber()==accountNumber) {
				return account1;
			}
		}
		return null;
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
