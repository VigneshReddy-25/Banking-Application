package main;

import model.Customer;
import repository.AccountRepository;
import service.AccountService;
import model.Account;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccountRepository accountRepository = new AccountRepository();
        AccountService accountService = new AccountService(accountRepository);
        Customer customer1=new Customer(101,"Vignesh", "reddy@gmail.com", "9138808373", "Hyderabad");
        Customer customer2=new Customer(102,"Shiva", "shiva@gmail.com", "8138808373", "Hyderabad");
        Customer customer3=new Customer(103,"Loknath", "lok@gmail.com", "7138808373", "Hyderabad");
        Customer customer4=new Customer(101,"Vignesh", "dinesh@gmail.com", "6138808373", "Hyderabad");
        
        System.out.println(accountService.createAccount(1001,customer1) ? "Account Created Successfully..." : "Account not created...");
        System.out.println(accountService.createAccount(1002,customer2)? "Account Created Successfully..." : "Account not created...");
        System.out.println(accountService.createAccount(1003,customer3)? "Account Created Successfully..." : "Account not created...");
        System.out.println(accountService.createAccount(1001,customer4)? "Account Created Successfully..." : "Account not created...");    
        System.out.println(accountService.deposit(1001, 5000) ? "Deposited Successfully..." : "Deposit Declained...");
        System.out.println(accountService.withdraw(1001, 500) ? "Amount Withdrawn Successfull..." : "Withdrawn failed...");
        System.out.println(accountService.withdraw(1001, 5000) ? "Amount Withdrawn Successfull..." : "Withdrawn failed...");
        System.out.println(accountService.getAccount(1001).displayAccount());
        for (Account account : accountService.getAllAccounts()) {
            System.out.println(account.displayAccount());
        }
        	
	}

}
