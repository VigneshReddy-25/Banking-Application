package service;

import repository.AccountRepository;
import repository.TransactionRepository;

import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;
import model.Transaction;

import java.util.List;

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
	
	    boolean saved = transactionRepository.addTransaction(transaction);
	    if (!saved) {
	        throw new DatabaseOperationException("Failed to save deposit transaction.", null);
	    }
	
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

        boolean saved = transactionRepository.addTransaction(transac);
        if (!saved) {
	        throw new DatabaseOperationException("Failed to save withdraw transaction.", null);
	    }

        return true;
    }

    public Account createAccount(String accountHolderName) throws InvalidAccountDetailsException, DatabaseOperationException {

        if (accountHolderName == null || accountHolderName.trim().isEmpty()) {
            throw new InvalidAccountDetailsException("Account holder name cannot be empty");
        }

        Account account = new Account(0, accountHolderName);

        if (accountRepository.addAccount(account)) {
            return account;
        }

        throw new DatabaseOperationException("Failed to create account.", null);
    }

    public SavingsAccount createSavingsAccount(String accountHolderName, double interestRate) throws InvalidAccountDetailsException, DatabaseOperationException {

        if (accountHolderName == null || accountHolderName.trim().isEmpty()) {
            throw new InvalidAccountDetailsException("Account holder name cannot be empty");
        }

        if (interestRate < 0) {
            throw new InvalidAccountDetailsException("Interest rate cannot be negative");
        }

        SavingsAccount account =
                new SavingsAccount(0, accountHolderName, interestRate);

        if (accountRepository.addAccount(account)) {
            return account;
        }

        throw new DatabaseOperationException("Failed to create savings account.", null);
    }

    public CurrentAccount createCurrentAccount(String accountHolderName, double overdraftLimit) throws InvalidAccountDetailsException, DatabaseOperationException {

        if (accountHolderName == null || accountHolderName.trim().isEmpty()) {
            throw new InvalidAccountDetailsException("Account holder name cannot be empty");
        }

        if (overdraftLimit < 0) {
            throw new InvalidAccountDetailsException("Overdraft limit cannot be negative");
        }

        CurrentAccount account = new CurrentAccount(0, accountHolderName, overdraftLimit);

        if (accountRepository.addAccount(account)) {
            return account;
        }

        throw new DatabaseOperationException("Failed to create current account.", null);
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

    public boolean closeAccount(long accountNumber)throws InvalidAccountDetailsException, DatabaseOperationException {

        if (accountNumber <= 0) {
            throw new InvalidAccountDetailsException("Account number must be greater than zero");
        }

        Account account = accountRepository.findAccount(accountNumber);

        if (account == null) {
            return false;
        }

        transactionRepository.removeTransaction(accountNumber);

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