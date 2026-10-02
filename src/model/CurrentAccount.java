package model;

import Exception.InsufficientBalanceException;
import Exception.InvalidAmountException;

public class CurrentAccount extends Account {

    private double overdraftLimit;

    public CurrentAccount(long accountNumber, String accountHolderName, double overdraftLimit) {
        super(accountNumber, accountHolderName);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public boolean withdraw(double amount) throws InvalidAmountException, InsufficientBalanceException {

        if (amount <= 0 )  {
        	throw new InvalidAmountException("Amount must be greater than zero");
        }
        else if(amount > (getBalance() + overdraftLimit)) {
        	throw new InsufficientBalanceException("Insufficient balance. Available balance: " + getBalance());
        }

        double balance = getBalance() - amount;
        setBalance(balance);

        return true;
    }
}