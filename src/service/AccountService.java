package service;

import repository.AccountRepository;
import repository.TransactionRepository;

import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;
import model.Transaction;

import java.util.List;

public class AccountService {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    private long transactionId = 1;

    public AccountService(AccountRepository accountRepository,
                          TransactionRepository transactionRepository) {

        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public boolean deposit(long accountNumber, double amount) {

        Account ac1 = accountRepository.findAccount(accountNumber);

        if (ac1 == null || amount <= 0) {
            return false;
        }

        double currentBalance = ac1.getBalance() + amount;

        ac1.setBalance(currentBalance);

        Transaction transac =
                new Transaction(
                        transactionId,
                        accountNumber,
                        "DEPOSIT",
                        amount,
                        currentBalance
                );

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

        Transaction transac =
                new Transaction(
                        transactionId,
                        accountNumber,
                        "WITHDRAW",
                        amount,
                        account.getBalance()
                );

        transactionRepository.addTransaction(transac);

        transactionId++;

        return true;
    }

    public boolean createAccount(long accountNumber,
                                 String accountHolderName) {

        Account account =
                new Account(accountNumber, accountHolderName);

        return accountRepository.addAccount(account);
    }

    public boolean createSavingsAccount(long accountNumber,
                                        String accountHolderName,
                                        double interestRate) {

        SavingsAccount account =
                new SavingsAccount(
                        accountNumber,
                        accountHolderName,
                        interestRate
                );

        return accountRepository.addAccount(account);
    }

    public boolean createCurrentAccount(long accountNumber,
                                        String accountHolderName,
                                        double overdraftLimit) {

        CurrentAccount account =
                new CurrentAccount(
                        accountNumber,
                        accountHolderName,
                        overdraftLimit
                );

        return accountRepository.addAccount(account);
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

        return transactionRepository
                .getTransactionsByAccount(accountNumber);
    }

    public List<Transaction> getAllTransactions() {

        return transactionRepository.getAllTransactions();
    }
}