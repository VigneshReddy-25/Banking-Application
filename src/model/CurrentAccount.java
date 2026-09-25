package model;

public class CurrentAccount extends Account {
	private double overdraftLimit;
	
	public CurrentAccount(long accountNumber, Customer customer,double overdraftLimit) {
		super(accountNumber, customer);
		this.overdraftLimit=overdraftLimit;
	}
	
	public double getOverdraftLimit() {
		return overdraftLimit;
	}
	
	public void setOverdraftLimit(double overdraftLimit) {
		this.overdraftLimit=overdraftLimit;
	}
	@Override
    public boolean withdraw(double amount) {
		
        if (amount <= 0 || amount > (getBalance()+overdraftLimit)) {
            return false;
        }
        double balance=getBalance() - amount;
        setBalance(balance); 
        return true;
    }
}
