package model;

import Exception.InsufficientBalanceException;
import Exception.InvalidAmountException;

public class Account {

    private long accountNumber;
    private String accountHolderName;
    private double balance;

    public Account(long accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean withdraw(double amount) throws InsufficientBalanceException, InvalidAmountException {
    	if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero");
        }
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance. Available balance: " + balance);
        }

        balance = balance - amount;
        return true;
    }

    public String displayAccount() {
        return "Account Number: " + accountNumber
                + " Account Holder Name: " + accountHolderName
                + " Balance: " + balance;
    }
}