package model;

public class Transaction {

	private long transactionId;
	private long accountNumber;
	private String transactionType;
	private double amount;
	private double balanceAfterTransaction;
	public Transaction(long accountNumber ,String transactionType,double amount,double balanceAfterTransaction) {
		this.accountNumber=accountNumber;
		this.transactionType=transactionType;
		this.amount=amount;
		this.balanceAfterTransaction=balanceAfterTransaction;
	}
	public long getTransactionId() {
		return transactionId;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public double getAmount() {
		return amount;
	}
	public double getBalanceAfterTransaction() {
		return balanceAfterTransaction;
	}
	
	public void setTransactionId(long transactionId) {
		this.transactionId=transactionId;
	}
	
	public String displayTransaction() {
		return "Transaction ID: "+ transactionId+
				"\n Account Number: "+ accountNumber+
				"\n Transaction Type: "+transactionType+
				"\n Amount: "+amount+
				"\n Balance After Transaction: "+balanceAfterTransaction;
	}
}
