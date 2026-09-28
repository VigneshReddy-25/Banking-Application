package main;

import model.Customer;
import model.Transaction;
import repository.AccountRepository;
import repository.CustomerRepository;
import service.AccountService;
import service.CustomerService;
import model.Account;
import repository.TransactionRepository;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccountRepository accountRepository = new AccountRepository();
		TransactionRepository transactionRepository=new TransactionRepository();
        AccountService accountService = new AccountService(accountRepository,transactionRepository);
        Customer customer1=new Customer(101,"Vignesh", "reddy@gmail.com", "9138808373", "Hyderabad");
        Customer customer2=new Customer(102,"Shiva", "shiva@gmail.com", "8138808373", "Hyderabad");
        Customer customer3=new Customer(103,"Loknath", "lok@gmail.com", "7138808373", "Hyderabad");
        Customer customer4=new Customer(101,"Vignesh", "dinesh@gmail.com", "6138808373", "Hyderabad");
        
        System.out.println(
                accountService.createAccount(1001, customer1)
                ? "Normal Account Created Successfully"
                : "Normal Account Creation Failed"
        );

        // -------------------------
        // Create Customer
        // -------------------------
        CustomerRepository customerRepo=new CustomerRepository();
        
        CustomerService customerService=new CustomerService(customerRepo);
		System.out.println(
                customerService.createCustomer(321001, "Vignesh", "reddy@gmail.com", "9182406830", "13-54, Hazi nagar") 
                ?   "Customer Account Created Successfully" : "Customer Account Creation Failed");
		
		
		
		
        // -------------------------
        // Create Savings Account
        // -------------------------

        System.out.println(
                accountService.createSavingsAccount(2001, customer2, 4.5)
                ? "Savings Account Created Successfully"
                : "Savings Account Creation Failed"
        );


        // -------------------------
        // Create Current Account
        // -------------------------

        System.out.println(
                accountService.createCurrentAccount(3001, customer3, 5000)
                ? "Current Account Created Successfully"
                : "Current Account Creation Failed"
        );


        // -------------------------
        // Duplicate Account Test
        // -------------------------

        System.out.println(
                accountService.createAccount(1001, customer4)
                ? "Duplicate Account Created"
                : "Duplicate Account Creation Failed"
        );


        // -------------------------
        // Normal Account Test
        // -------------------------

        System.out.println("\n--- Normal Account Test ---");

        System.out.println(
                accountService.deposit(1001, 5000)
                ? "Deposit Successful"
                : "Deposit Failed"
        );

        System.out.println(
                accountService.withdraw(1001, 1000)
                ? "Withdrawal Successful"
                : "Withdrawal Failed"
        );


        // -------------------------
        // Savings Account Test
        // -------------------------

        System.out.println("\n--- Savings Account Test ---");

        System.out.println(
                accountService.deposit(2001, 5000)
                ? "Deposit Successful"
                : "Deposit Failed"
        );

        System.out.println(
                accountService.withdraw(2001, 2000)
                ? "Withdrawal Successful"
                : "Withdrawal Failed"
        );

        // Balance = 3000
        // Trying to withdraw 4000 should fail

        System.out.println(
                accountService.withdraw(2001, 4000)
                ? "Withdrawal Successful"
                : "Withdrawal Failed"
        );


        // -------------------------
        // Current Account Test
        // -------------------------

        System.out.println("\n--- Current Account Test ---");

        System.out.println(
                accountService.deposit(3001, 5000)
                ? "Deposit Successful"
                : "Deposit Failed"
        );

        // Balance = 5000
        // Overdraft = 5000
        // Total available = 10000

        System.out.println(
                accountService.withdraw(3001, 7000)
                ? "Withdrawal Successful"
                : "Withdrawal Failed"
        );

        // Balance should now be -2000

        System.out.println(
                accountService.withdraw(3001, 4000)
                ? "Withdrawal Successful"
                : "Withdrawal Failed"
        );

        // This should fail because balance would become -6000
        // Maximum overdraft is -5000


        // -------------------------
        // Display Individual Accounts
        // -------------------------

        System.out.println("\n--- Individual Accounts ---");

        System.out.println(
                accountService.getAccount(1001).displayAccount()
        );

        System.out.println(
                accountService.getAccount(2001).displayAccount()
        );

        System.out.println(
                accountService.getAccount(3001).displayAccount()
        );


        // -------------------------
        // Display All Accounts
        // -------------------------

        System.out.println("\n--- All Accounts ---");

        for (Account account : accountService.getAllAccounts()) {
            System.out.println(account.displayAccount());
        }
        System.out.println("\n--- All Transactions ---");

        for (Transaction transaction : accountService.getAllTransactions()) {
            System.out.println(transaction.displayTransaction());
        }
        	
	}

}
