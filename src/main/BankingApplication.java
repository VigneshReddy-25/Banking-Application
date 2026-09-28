package main;

import model.Account;
import model.Transaction;
import model.Bank;

import repository.AccountRepository;
import repository.TransactionRepository;

import service.AccountService;

import java.util.List;

public class BankingApplication {

    public static void main(String[] args) {

        AccountRepository accountRepository =
                new AccountRepository();

        TransactionRepository transactionRepository =
                new TransactionRepository();

        AccountService accountService =
                new AccountService(
                        accountRepository,
                        transactionRepository
                );

        // =========================
        // Bank Details
        // =========================
        
        Bank bank=new Bank("Union Bank","UBIN0803901");
        System.out.println(bank.displayBank());
        

        // =========================
        // CREATE ACCOUNTS
        // =========================

        accountService.createAccount(
                1001,
                "Vignesh"
        );

        accountService.createSavingsAccount(
                2001,
                "Shiva",
                4.5
        );

        accountService.createCurrentAccount(
                3001,
                "Loknath",
                5000
        );


        // =========================
        // DUPLICATE ACCOUNT TEST
        // =========================

        boolean duplicate =
                accountService.createAccount(
                        1001,
                        "Vignesh"
                );

        System.out.println("Duplicate Account Created: " + duplicate);


        // =========================
        // DEPOSIT
        // =========================

        System.out.println(
                "Deposit: " +
                accountService.deposit(1001, 5000)
        );

        System.out.println(
                "Deposit: " +
                accountService.deposit(2001, 10000)
        );

        System.out.println(
                "Deposit: " +
                accountService.deposit(3001, 5000)
        );


        // =========================
        // WITHDRAW
        // =========================

        System.out.println(
                "Withdraw: " +
                accountService.withdraw(1001, 2000)
        );

        System.out.println(
                "Withdraw: " +
                accountService.withdraw(2001, 3000)
        );

        // Current account can use overdraft
        System.out.println(
                "Current Account Withdraw: " +
                accountService.withdraw(3001, 8000)
        );


        // =========================
        // DISPLAY ONE ACCOUNT
        // =========================

        Account account =
                accountService.getAccount(1001);

        if (account != null) {
            System.out.println(account.displayAccount());
        }


        // =========================
        // DISPLAY ALL ACCOUNTS
        // =========================

        System.out.println("\n--- ALL ACCOUNTS ---");

        List<Account> accounts =
                accountService.getAllAccounts();

        for (Account ac : accounts) {
            System.out.println(ac.displayAccount());
        }


        // =========================
        // DISPLAY TRANSACTIONS
        // =========================

        System.out.println("\n--- TRANSACTIONS FOR ACCOUNT 1001 ---");

        List<Transaction> transactions =
                accountService.getTransactionsByAccount(1001);

        for (Transaction transaction : transactions) {
            System.out.println(transaction.displayTransaction());
        }


        // =========================
        // DISPLAY ALL TRANSACTIONS
        // =========================

        System.out.println("\n--- ALL TRANSACTIONS ---");

        List<Transaction> allTransactions =
                accountService.getAllTransactions();

        for (Transaction transaction : allTransactions) {
            System.out.println(transaction.displayTransaction());
        }


        // =========================
        // CLOSE ACCOUNT
        // =========================

        boolean closed =
                accountService.closeAccount(1001);

        System.out.println(
                "\nAccount 1001 Closed: " + closed
        );


        // =========================
        // VERIFY ACCOUNT REMOVED
        // =========================

        Account check =
                accountService.getAccount(1001);

        System.out.println(
                "Account 1001 After Closing: " + check
        );
    }
}