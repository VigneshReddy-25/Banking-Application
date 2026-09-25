package service;

import repository.AccountRepository;
import model.Customer;
import model.SavingsAccount;
import model.CurrentAccount;

import java.util.List;

import model.Account;
public class AccountService {

	private AccountRepository accountRepository;
	
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository=accountRepository;
	}
	public boolean deposit(long accountNumber, double amount) {
			Account ac1=accountRepository.findAccount(accountNumber);
			if (ac1 == null || amount <= 0) {
			    return false;
			}
			ac1.setBalance(ac1.getBalance()+amount);
		return true;
	}
	
	public boolean withdraw(long accountNumber, double amount) {
	    Account account = accountRepository.findAccount(accountNumber);

	    if (account == null) {
	        return false;
	    }

	    return account.withdraw(amount);
	}
	
	public boolean createAccount(long accountNumber, Customer customer) {
	    Account account = new Account(accountNumber, customer);
	    boolean b=accountRepository.addAccount(account);
	    return b;
	}
	
	public boolean createSavingsAccount(long accountNumber, Customer customer,double interestRate) {
		SavingsAccount account = new SavingsAccount(accountNumber, customer, interestRate);
	    boolean b=accountRepository.addAccount(account);
	    return b;
	}
	
	public boolean createCurrentAccount(long accountNumber, Customer customer,double overdraftLimit) {
		CurrentAccount account = new CurrentAccount(accountNumber, customer, overdraftLimit);
	    boolean b=accountRepository.addAccount(account);
	    return b;
	}
	
	
	public Account getAccount(long accountNumber) {
		return accountRepository.findAccount(accountNumber);
	}
	public List<Account> getAllAccounts(){
		return accountRepository.getAllAccounts();
	}
	public boolean closeAccount(long accountNumber) {
		return accountRepository.removeAccount(accountNumber);
	}
}
