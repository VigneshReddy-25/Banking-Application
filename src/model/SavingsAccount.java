package model;


public class SavingsAccount extends Account {
	private double interestRate;
	
	public SavingsAccount(long accountNumber, Customer customer, double interestRate) {
		super(accountNumber,customer);
		this.interestRate=interestRate;
	}
	
	public double getInterestRate() {
		return interestRate;
	}
	
	public void setInterestRate(double interestRate) {
		this.interestRate=interestRate;
	}
	
	@Override
	public boolean withdraw(double amount) {
	    return super.withdraw(amount);
	}
}
