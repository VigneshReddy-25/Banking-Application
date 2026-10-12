package service;

import repository.AccountRepository;
import repository.TransactionRepository;

import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;
import model.Transaction;

import java.util.List;

import Exception.AccountAlreadyExistsException;
import Exception.AccountNotFoundException;
import Exception.DatabaseOperationException;
import Exception.InsufficientBalanceException;
import Exception.InvalidAccountDetailsException;
import Exception.InvalidAmountException;

public class AccountService {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;


   
    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {

        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public boolean deposit(long accountNumber, double amount) throws AccountNotFoundException, InvalidAmountException, InvalidAccountDetailsException, DatabaseOperationException {

	    if (accountNumber <= 0) {
	        throw new InvalidAccountDetailsException("Account number must be greater than zero");
	    }
	
	    Account account = accountRepository.findAccount(accountNumber);
	
	    if (account == null) {
	        throw new AccountNotFoundException("Account not found: " + accountNumber);
	    }
	
	    if (amount <= 0) {
	        throw new InvalidAmountException("Amount must be greater than zero");
	    }
	
	    double newBalance = account.getBalance() + amount;
	
	    boolean updated = accountRepository.updateBalance(accountNumber, newBalance);
	
	    if (!updated) {
	        throw new DatabaseOperationException("Failed to update account balance.", null);
	    }
	
	    account.setBalance(newBalance);
	
	    Transaction transaction = new Transaction(accountNumber, "DEPOSIT", amount, newBalance);
	
	    transactionRepository.addTransaction(transaction);
	
	    return true;
    }

    public boolean withdraw(long accountNumber, double amount) throws AccountNotFoundException, InvalidAmountException, InsufficientBalanceException, InvalidAccountDetailsException, DatabaseOperationException {

    	if (accountNumber <= 0) {
            throw new InvalidAccountDetailsException("Account number must be greater than zero");
        }
    	
    	Account account = accountRepository.findAccount(accountNumber);

        if (account == null) {
        	throw new AccountNotFoundException("Account not found " +accountNumber);
        }

        if(amount <= 0) {
        	throw new InvalidAmountException("Amount must be greater than zero");
        }
        
        account.withdraw(amount);
        
        boolean updated = accountRepository.updateBalance(accountNumber, account.getBalance());
        
        if (!updated) {
	        throw new DatabaseOperationException("Failed to update account balance.", null);
	    }

        Transaction transac = new Transaction(accountNumber, "WITHDRAW", amount, account.getBalance());

        transactionRepository.addTransaction(transac);

        return true;
    }

    public boolean createAccount(long accountNumber, String accountHolderName) throws AccountAlreadyExistsException, InvalidAccountDetailsException, DatabaseOperationException {

        if(accountNumber <= 0) {
            throw new InvalidAccountDetailsException("Account number must be greater than zero");
        }

        if(accountHolderName == null || accountHolderName.trim().isEmpty()) {
            throw new InvalidAccountDetailsException("Account holder name cannot be empty");
        }
        Account account = new Account(accountNumber, accountHolderName);
        
        boolean created = accountRepository.addAccount(account);
        if (!created) {
            throw new AccountAlreadyExistsException("Account already exists: " + accountNumber);
        }

        return true;
    }

    public boolean createSavingsAccount(long accountNumber, String accountHolderName, double interestRate) throws AccountAlreadyExistsException, InvalidAccountDetailsException, DatabaseOperationException {

	    if(accountNumber <= 0) {
	        throw new InvalidAccountDetailsException("Account number must be greater than zero");
	    }
	
	    if(accountHolderName == null || accountHolderName.trim().isEmpty()) {
	        throw new InvalidAccountDetailsException("Account holder name cannot be empty");
	    }
	
	    if(interestRate < 0) {
	        throw new InvalidAccountDetailsException("Interest rate cannot be negative");
	    }
	
	    SavingsAccount account =
	            new SavingsAccount(accountNumber, accountHolderName, interestRate);
	
	    boolean created = accountRepository.addAccount(account);
	
	    if(!created) {
	        throw new AccountAlreadyExistsException("Account already exists: " + accountNumber);
	    }
	
	    return true;
    }

    public boolean createCurrentAccount(long accountNumber, String accountHolderName, double overdraftLimit) throws AccountAlreadyExistsException, InvalidAccountDetailsException, DatabaseOperationException {

	    if(accountNumber <= 0) {
	        throw new InvalidAccountDetailsException("Account number must be greater than zero");
	    }
	
	    if(accountHolderName == null || accountHolderName.trim().isEmpty()) {
	        throw new InvalidAccountDetailsException("Account holder name cannot be empty");
	    }
	
	    if(overdraftLimit < 0) {
	        throw new InvalidAccountDetailsException("Overdraft limit cannot be negative");
	    }
	
	    CurrentAccount account =
	            new CurrentAccount(accountNumber, accountHolderName, overdraftLimit);
	
	    boolean created = accountRepository.addAccount(account);
	
	    if(!created) {
	        throw new AccountAlreadyExistsException("Account already exists: " + accountNumber);
	    }
	
	    return true;
	}

    public Account getAccount(long accountNumber) throws InvalidAccountDetailsException, DatabaseOperationException {

        if (accountNumber <= 0) {
            throw new InvalidAccountDetailsException("Account number must be greater than zero");
        }

        return accountRepository.findAccount(accountNumber);
    }

    public List<Account> getAllAccounts() throws DatabaseOperationException {

        return accountRepository.getAllAccounts();
    }

    public boolean closeAccount(long accountNumber) throws InvalidAccountDetailsException, DatabaseOperationException {

        if (accountNumber <= 0) {
            throw new InvalidAccountDetailsException("Account number must be greater than zero");
        }

        return accountRepository.removeAccount(accountNumber);
    }

    public List<Transaction> getTransactionsByAccount(long accountNumber) throws InvalidAccountDetailsException, AccountNotFoundException, DatabaseOperationException {

    	if (accountNumber <= 0) {
            throw new InvalidAccountDetailsException("Account number must be greater than zero");
        }

        Account account = accountRepository.findAccount(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException("Account not found: " + accountNumber);
        }
    	
        return transactionRepository.getTransactionsByAccount(accountNumber);
    }

    public List<Transaction> getAllTransactions() throws DatabaseOperationException {

        return transactionRepository.getAllTransactions();
    }
}