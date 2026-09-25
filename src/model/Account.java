package model;	
public class Account {

	private long accountNumber;
	private Customer customer;
//    private String accountHolderName;
    private double balance;
    
    
    
    public Account(long accountNumber,Customer customer) {
    	this.accountNumber=accountNumber;
    	this.customer=customer;
    	this.balance=0;
    }
    
    public long getAccountNumber() {
    	return accountNumber;
    }
    public Customer getCustomer() {
    	return customer;
    }
    public double getBalance() {
    	return balance;
    }
    
    public void setAccountNumber(long accountNumber) {
    	this.accountNumber=accountNumber;
    }
    public void setCustomer(Customer customer) {
    	this.customer=customer;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance = balance - amount;
        return true;
    }
	
    public String displayAccount() {
    	return "Account Number: "+ accountNumber
    			+" Account Holder Name: "+ customer.getName() +
    			" Balance: "+balance;
    }
	
}
