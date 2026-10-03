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
import Exception.InsufficientBalanceException;
import Exception.InvalidAccountDetailsException;
import Exception.InvalidAmountException;

public class AccountService {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    private long transactionId = 1;

   
    public AccountService(AccountRepository accountRepository,
                          TransactionRepository transactionRepository) {

        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public boolean deposit(long accountNumber, double amount) throws AccountNotFoundException, InvalidAmountException {

        Account ac1 = accountRepository.findAccount(accountNumber);

        if (ac1 == null) {
            throw new AccountNotFoundException("Account not found " +accountNumber);
        }
        else if(amount <= 0) {
        	throw new InvalidAmountException("Amount must be greater than zero");
        }

        double currentBalance = ac1.getBalance() + amount;

        ac1.setBalance(currentBalance);

        Transaction transac = new Transaction(transactionId, accountNumber, "DEPOSIT", amount, currentBalance);

        transactionId++;

        transactionRepository.addTransaction(transac);

        return true;
    }

    public boolean withdraw(long accountNumber, double amount) throws AccountNotFoundException, InvalidAmountException, InsufficientBalanceException {

        Account account = accountRepository.findAccount(accountNumber);

        if (account == null) {
        	throw new AccountNotFoundException("Account not found " +accountNumber);
        }

        if(amount <= 0) {
        	throw new InvalidAmountException("Amount must be greater than zero");
        }
        
        account.withdraw(amount);

        Transaction transac = new Transaction(transactionId, accountNumber, "WITHDRAW", amount, account.getBalance());

        transactionRepository.addTransaction(transac);

        transactionId++;

        return true;
    }

    public boolean createAccount(long accountNumber, String accountHolderName) throws AccountAlreadyExistsException, InvalidAccountDetailsException {

        Account account = new Account(accountNumber, accountHolderName);
        if(accountNumber <= 0) {
            throw new InvalidAccountDetailsException("Account number must be greater than zero");
        }

        if(accountHolderName == null || accountHolderName.trim().isEmpty()) {
            throw new InvalidAccountDetailsException("Account holder name cannot be empty");
        }
        boolean created = accountRepository.addAccount(account);
        if (!created) {
            throw new AccountAlreadyExistsException("Account already exists: " + accountNumber);
        }

        return true;
    }

    public boolean createSavingsAccount(long accountNumber, String accountHolderName, double interestRate) throws AccountAlreadyExistsException, InvalidAccountDetailsException {

        SavingsAccount account = new SavingsAccount(accountNumber, accountHolderName, interestRate);
        
        if(accountNumber <= 0) {
            throw new InvalidAccountDetailsException("Account number must be greater than zero");
        }

        if(accountHolderName == null || accountHolderName.trim().isEmpty()) {
            throw new InvalidAccountDetailsException("Account holder name cannot be empty");
        }
        if(interestRate < 0) {
            throw new InvalidAccountDetailsException("Interest rate cannot be negative");
        }
        
        boolean created = accountRepository.addAccount(account);
        
        if (!created) {
            throw new AccountAlreadyExistsException("Account already exists: " + accountNumber);
        }

        return true;
    }

    public boolean createCurrentAccount(long accountNumber, String accountHolderName, double overdraftLimit) throws AccountAlreadyExistsException, InvalidAccountDetailsException {

        CurrentAccount account = new CurrentAccount(accountNumber, accountHolderName, overdraftLimit);
        
        if(accountNumber <= 0) {
            throw new InvalidAccountDetailsException("Account number must be greater than zero");
        }

        if(accountHolderName == null || accountHolderName.trim().isEmpty()) {
            throw new InvalidAccountDetailsException("Account holder name cannot be empty");
        }
        if (overdraftLimit < 0) {
            throw new InvalidAccountDetailsException("Overdraft limit cannot be negative");
        }
        
        boolean created = accountRepository.addAccount(account);
        
        if (!created) {
            throw new AccountAlreadyExistsException("Account already exists: " + accountNumber);
        }

        return true;
    }

    public Account getAccount(long accountNumber) {

        return accountRepository.findAccount(accountNumber);
    }

    public List<Account> getAllAccounts() {

        return accountRepository.getAllAccounts();
    }

    public boolean closeAccount(long accountNumber) {

        return accountRepository.removeAccount(accountNumber);
    }

    public List<Transaction> getTransactionsByAccount(long accountNumber) {

        return transactionRepository.getTransactionsByAccount(accountNumber);
    }

    public List<Transaction> getAllTransactions() {

        return transactionRepository.getAllTransactions();
    }
}