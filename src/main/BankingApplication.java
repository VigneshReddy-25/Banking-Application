package main;

import java.util.List;
import java.util.Scanner;

import Exception.AccountNotFoundException;
import Exception.DatabaseOperationException;
import Exception.InsufficientBalanceException;
import Exception.InvalidAccountDetailsException;
import Exception.InvalidAmountException;
import model.Account;
import model.CurrentAccount;
import model.SavingsAccount;
import model.Transaction;
import repository.AccountRepository;
import repository.TransactionRepository;
import java.util.InputMismatchException;

import service.AccountService;

public class BankingApplication {

    public static void main(String[] args) {
    	
    	AccountRepository accountRepository = new AccountRepository();
    	TransactionRepository transactionRepository = new TransactionRepository();
    	
    	AccountService accountService = new AccountService(accountRepository, transactionRepository);
    	
    	Scanner scanner=new Scanner(System.in);

        while(true) {
        	System.out.println("1. Create Account");
        	System.out.println("2. Create Savings Account");
        	System.out.println("3. Create Current Account");
        	System.out.println("4. Deposit");
        	System.out.println("5. Withdraw");
        	System.out.println("6. View Account");
        	System.out.println("7. View All Accounts");
        	System.out.println("8. View Account Transactions");
        	System.out.println("9. View All Transactions");
        	System.out.println("10. Close Account");
        	System.out.println("0. Exit");
        	
        	System.out.println("Enter your choice:");

        	int choice;

        	try {
        	    choice = scanner.nextInt();
        	}
        	catch (InputMismatchException e) {
        	    System.out.println("Invalid input. Please enter a number.");
        	    scanner.nextLine();
        	    continue;
        	}
        	
        	try {
	        	switch(choice) {
		        	case 0:
		        	    System.out.println("Thank you for using Banking Application");
		        	    scanner.close();
		        	    return;
		        	    
		        	case 1:
		        	    try {
		        	        scanner.nextLine();

		        	        System.out.println("Enter account holder name: ");
		        	        String accountHolderName = scanner.nextLine();

		        	        Account account = accountService.createAccount(accountHolderName);

		        	        System.out.println("Account created successfully.");
		        	        System.out.println("Generated Account Number: " + account.getAccountNumber());
		        	    }
		        	    catch (InputMismatchException e) {
		        	        System.out.println("Invalid input. Please enter valid details.");
		        	        scanner.nextLine();
		        	    }
		        	    catch (InvalidAccountDetailsException e) {
		        	        System.out.println(e.getMessage());
		        	    }
		        	    break;
	        			
		        	case 2:
					    try {
					        scanner.nextLine();
					
					        System.out.println("Enter account holder name: ");
					        String savAccountHolderName = scanner.nextLine();
					
					        System.out.println("Enter Interest Rate: ");
					        double interestRate = scanner.nextDouble();
					
					        SavingsAccount account = accountService.createSavingsAccount(
					                        savAccountHolderName, interestRate);
					
					        System.out.println("Savings Account created successfully.");
					        System.out.println("Generated Account Number: "
					                + account.getAccountNumber());
					    }
					    catch (InputMismatchException e) {
					        System.out.println("Invalid input. Please enter valid numbers.");
					        scanner.nextLine();
					    }
					    catch (InvalidAccountDetailsException e) {
					        System.out.println(e.getMessage());
					    }
					    break;
	        			
	        		case 3:
					    try {
					        scanner.nextLine();
					
					        System.out.println("Enter account holder name: ");
					        String currAccountHolderName = scanner.nextLine();
					
					        System.out.println("Enter Overdraft Limit: ");
					        double overdraftLimit = scanner.nextDouble();
					
					        CurrentAccount account = accountService.createCurrentAccount(currAccountHolderName, overdraftLimit);
					
					        System.out.println("Current Account created successfully.");
					        System.out.println("Generated Account Number: " + account.getAccountNumber());
					    }
					    catch (InputMismatchException e) {
					        System.out.println("Invalid input. Please enter valid numbers.");
					        scanner.nextLine();
					    }
					    catch (InvalidAccountDetailsException e) {
					        System.out.println(e.getMessage());
					    }
					    break;
	        			
	        		case 4:
	        		    try {
	        		        System.out.println("Enter account number: ");
	        		        long accountNumber1 = scanner.nextLong();
	
	        		        System.out.println("Enter amount to deposit: ");
	        		        double amount = scanner.nextDouble();
	
	        		        if(accountService.deposit(accountNumber1, amount))
	        		            System.out.println("Deposit successful...");
	        		    }
	        		    catch(InputMismatchException e) {
	        		        System.out.println("Invalid input. Please enter valid numbers.");
	        		        scanner.nextLine();
	        		    }
	        		    catch(AccountNotFoundException | InvalidAmountException | InvalidAccountDetailsException e) {
	        		        System.out.println(e.getMessage());
	        		    }
	        		    break;
	        			
	        		case 5:
	        		    try {
	        		        System.out.println("Enter account number: ");
	        		        long accountNumber2 = scanner.nextLong();
	
	        		        System.out.println("Enter amount to withdraw: ");
	        		        double amount1 = scanner.nextDouble();
	
	        		        if(accountService.withdraw(accountNumber2, amount1))
	        		            System.out.println("Withdraw successful...");
	        		    }
	        		    catch(InputMismatchException e) {
	        		        System.out.println("Invalid input. Please enter valid numbers.");
	        		        scanner.nextLine();
	        		    }
	        		    catch(AccountNotFoundException | InvalidAmountException | InsufficientBalanceException | InvalidAccountDetailsException e) {
	
	        		        System.out.println(e.getMessage());
	        		    }
	        		    break;
	        			
	        		case 6:
	        		    try {
	        		        System.out.println("Enter account number: ");
	        		        long getAccountNumber = scanner.nextLong();
	
	        		        Account acc1 = accountService.getAccount(getAccountNumber);
	
	        		        if(acc1 != null)
	        		            System.out.println(acc1.displayAccount());
	        		        else
	        		            System.out.println("Account not found...");
	        		    }
	        		    catch(InvalidAccountDetailsException e) {
	        		    	System.out.println(e.getMessage());        		    }
	        		    catch(InputMismatchException e) {
	        		        System.out.println("Invalid account number. Please enter a number.");
	        		        scanner.nextLine();
	        		    }
	        		    break;
	        			
	        		case 7:
	        		    List<Account> accounts = accountService.getAllAccounts();

	        		    if (accounts.isEmpty()) {
	        		        System.out.println("No accounts found.");
	        		    } else {
	        		        for (Account account : accounts) {
	        		            System.out.println(account.displayAccount());
	        		        }
	        		    }
	        		    break;
	        		    
	        		case 8:
	        		    try {
	        		        System.out.println("Enter account number: ");
	        		        long getAccountNumber1 = scanner.nextLong();
	
	        		        List<Transaction> transactions =
	        		                accountService.getTransactionsByAccount(getAccountNumber1);
	
	        		        if(transactions.isEmpty()) {
	        		            System.out.println("No transactions found");
	        		        }
	        		        else {
	        		            for(Transaction transaction : transactions) {
	        		                System.out.println(transaction.displayTransaction());
	        		            }
	        		        }
	        		    }
	        		    catch (InputMismatchException e) {
	        		        System.out.println("Invalid account number. Please enter a number.");
	        		        scanner.nextLine();
	        		    }
	        		    catch (InvalidAccountDetailsException | AccountNotFoundException e) {
	        		        System.out.println(e.getMessage());
	        		    }
	        		    break;
	        		    
	        		case 9:
	        		    List<Transaction> transactions = accountService.getAllTransactions();

	        		    if (transactions.isEmpty()) {
	        		        System.out.println("No transactions found.");
	        		    } else {
	        		        for (Transaction transaction : transactions) {
	        		            System.out.println(transaction.displayTransaction());
	        		        }
	        		    }
	        		    break;
	        			
	        		case 10:
	        		    try {
	        		        System.out.println("Enter account number: ");
	        		        long closeAccountNumber = scanner.nextLong();
	
	        		        if(accountService.closeAccount(closeAccountNumber))
	        		            System.out.println("Account has been deleted...");
	        		        else
	        		            System.out.println("Account not found...");
	        		    }
	        		    catch(InvalidAccountDetailsException e) {
	        		    	System.out.println(e.getMessage());
	        		    }
	        		    catch(InputMismatchException e) {
	        		        System.out.println("Invalid account number. Please enter a number.");
	        		        scanner.nextLine();
	        		    }
	        		    break;
	        		
	        		default:
	        		    System.out.println("Invalid choice...");
	        		    break;
	        	}
        	}
        	catch (DatabaseOperationException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }
        
    }
}