package repository;

import java.util.ArrayList;
import java.util.List;

import model.Transaction;

public class TransactionRepository {

	private List<Transaction> transactions = new ArrayList<>();
	
	public boolean addTransaction(Transaction transaction) {
		transactions.add(transaction);
		return true;
	}
	public List<Transaction> getTransactionsByAccount(long accountNumber) {
		List<Transaction> result = new ArrayList<>();
		for (Transaction transaction : transactions) {
		    if (accountNumber == transaction.getAccountNumber()) {
		        result.add(transaction);
		    }
		}

		return result;
	}
	
	public List<Transaction> getAllTransactions(){
		return new ArrayList<>(transactions);
	}
}
