package service;

import repository.AccountRepository;
import model.Customer;
import model.SavingsAccount;
import model.CurrentAccount;
import repository.TransactionRepository;
import model.Transaction;

import java.util.List;

import model.Account;
public class AccountService {

	private AccountRepository accountRepository;
	private TransactionRepository transactionRepository;
	private long transactionId = 1;
	
	public AccountService(AccountRepository accountRepository,TransactionRepository transactionRepository) {
		this.accountRepository=accountRepository;
		this.transactionRepository=transactionRepository;
	}
	public boolean deposit(long accountNumber, double amount) {
			Account ac1=accountRepository.findAccount(accountNumber);
			if (ac1 == null || amount <= 0) {
			    return false;
			}
			double currentBalance=ac1.getBalance()+amount;
			ac1.setBalance(currentBalance);
			Transaction transac=new Transaction(transactionId,accountNumber,"DEPOSIT",amount, currentBalance);
			transactionId++;
			transactionRepository.addTransaction(transac);
		return true;
	}
	
	public boolean withdraw(long accountNumber, double amount) {
	    Account account = accountRepository.findAccount(accountNumber);

	    if (account == null) {
	        return false;
	    }
	    boolean success = account.withdraw(amount);

	    if (!success) {
	        return false;
	    }

	    Transaction transac=new Transaction(transactionId,accountNumber,"WITHDRAW",amount, account.getBalance());
	    transactionRepository.addTransaction(transac);
	    transactionId++;

	    return true;
	}
	
	public List<Transaction> getTransactionsByAccount(long accountNumber){
		List<Transaction> ls1=transactionRepository.getTransactionsByAccount(accountNumber);
		return ls1;
	}
	public List<Transaction> getAllTransactions(){
		return transactionRepository.getAllTransactions();
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
