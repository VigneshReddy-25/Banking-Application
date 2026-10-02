package model;

import Exception.InsufficientBalanceException;
import Exception.InvalidAmountException;

public class SavingsAccount extends Account {

    private double interestRate;

    public SavingsAccount(long accountNumber, String accountHolderName, double interestRate) {
        super(accountNumber, accountHolderName);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public boolean withdraw(double amount) throws InsufficientBalanceException, InvalidAmountException {
        return super.withdraw(amount);
    }
}