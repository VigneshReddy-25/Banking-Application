package model;

public class Transaction {

	private long transactionId;
	private long accountNumber;
	private String transactionType;
	private double amount;
	private double balanceAfterTransaction;
	public Transaction(long transactionId,long accountNumber ,String transactionType,double amount,double balanceAfterTransaction) {
		this.transactionId=transactionId;
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
	public String displayTransaction() {
		return "Transaction ID: "+ transactionId+
				" Account Number: "+ accountNumber+
				" Transaction Type: "+transactionType+
				" Amount: "+amount+
				" Balance After Transaction: "+balanceAfterTransaction;
	}
}
