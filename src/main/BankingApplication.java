package main;

import java.util.Scanner;

import service.AccountService;

public class BankingApplication {

    public static void main(String[] args) {
    	
    	Scanner scanner=new Scanner(System.in);
    	
    	AccountService accountSevice = new AccountService();

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

        	int choice= scanner.nextInt();
        	
        	switch(choice) {
        		case 1:
        			System.out.println("Enter account number:");
        			long accountNumber=scanner.nextLong();
        			System.out.println("Enter account holder name:");
        			scanner.nextLine();
        			String accountHolderName=scanner.nextLine();
        			System.out.println(accountSevice.createAccount(accountNumber,accountHolderName));
        			
        	}
        }
        
    }
}