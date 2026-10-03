package main;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import Exception.AccountAlreadyExistsException;
import Exception.AccountNotFoundException;
import Exception.InsufficientBalanceException;
import Exception.InvalidAccountDetailsException;
import Exception.InvalidAmountException;
import model.Account;
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
        	
        	switch(choice) {
	        	case 0:
	        	    System.out.println("Thank you for using Banking Application");
	        	    scanner.close();
	        	    return;
	        	    
	        	case 1:
	        	    try {
	        	        System.out.println("Enter account number: ");
	        	        long accountNumber = scanner.nextLong();

	        	        System.out.println("Enter account holder name: ");
	        	        scanner.nextLine();
	        	        String accountHolderName = scanner.nextLine();

	        	        if(accountService.createAccount(accountNumber, accountHolderName))
	        	            System.out.println("Account created successfully...");
	        	    }
	        	    catch(InputMismatchException e) {
	        	        System.out.println("Invalid account number. Please enter a number.");
	        	        scanner.nextLine();
	        	    }
	        	    catch(AccountAlreadyExistsException | InvalidAccountDetailsException e) {
	        	        System.out.println(e.getMessage());
	        	    }
	        	    break;
        			
	        	case 2:
	        	    try {
	        	        System.out.println("Enter account number: ");
	        	        long savAccountNumber = scanner.nextLong();

	        	        System.out.println("Enter account holder name: ");
	        	        scanner.nextLine();
	        	        String savAccountHolderName = scanner.nextLine();

	        	        System.out.println("Enter Interest Rate: ");
	        	        double interestRate = scanner.nextDouble();

	        	        if(accountService.createSavingsAccount(
	        	                savAccountNumber,
	        	                savAccountHolderName,
	        	                interestRate)) {

	        	            System.out.println("Savings Account created successfully...");
	        	        }
	        	    }
	        	    catch(InputMismatchException e) {
	        	        System.out.println("Invalid input. Please enter valid numbers.");
	        	        scanner.nextLine();
	        	    }
	        	    catch(AccountAlreadyExistsException | InvalidAccountDetailsException e) {
	        	        System.out.println(e.getMessage());
	        	    }
	        	    break;
        			
        		case 3:
        			System.out.println("Enter account number: ");
        			long currAccountNumber=scanner.nextLong();
        			System.out.println("Enter account holder name: ");
        			scanner.nextLine();
        			String currAccountHolderName=scanner.nextLine();
        			System.out.println("Enter Overdraft Limit: ");
        			double overdraftLimit=scanner.nextDouble();
        			try {
	        			if(accountService.createCurrentAccount(currAccountNumber, currAccountHolderName, overdraftLimit)) 
	        				System.out.println("Current Account created successfully...");
        			}
        			catch(AccountAlreadyExistsException | InvalidAccountDetailsException e) {
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
        		    catch(AccountNotFoundException | InvalidAmountException e) {
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
        		    catch(AccountNotFoundException |
        		          InvalidAmountException |
        		          InsufficientBalanceException e) {

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
        		    catch(InputMismatchException e) {
        		        System.out.println("Invalid account number. Please enter a number.");
        		        scanner.nextLine();
        		    }
        		    break;
        			
        		case 7:
        			List<Account> accounts=accountService.getAllAccounts();
        			Iterator<Account> ite=accounts.iterator();
        			while(ite.hasNext()) {
        				Account acc2=ite.next();
        				System.out.println(acc2.displayAccount());
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
        		    catch(InputMismatchException e) {
        		        System.out.println("Invalid account number. Please enter a number.");
        		        scanner.nextLine();
        		    }
        		    break;
        			
        		case 9:
        			List<Transaction> transactions1=accountService.getAllTransactions();
        			Iterator<Transaction> ite1=transactions1.iterator();
        			while(ite1.hasNext()) {
        				Transaction transaction1=ite1.next();
        				System.out.println(transaction1.displayTransaction());
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
        
    }
}