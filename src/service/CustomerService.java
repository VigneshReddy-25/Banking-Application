package service;

import java.util.ArrayList;

import model.Customer;
import repository.CustomerRepository;

public class CustomerService {

	private CustomerRepository customerRepo;
	
	public CustomerService(CustomerRepository customerRepo) {
		this.customerRepo=customerRepo;
	}
	public boolean createCustomer(long customerId, String name, String email, String phone, String address) {
		Customer ch1=new Customer(customerId, name, email, phone, address);
		boolean bl1=customerRepo.addCustomer(ch1);
		return bl1;
	}
	public Customer getCustomer(long customerId) {
		Customer ch1=customerRepo.findCustomer(customerId);
		return ch1;
	}
	public ArrayList<Customer> getAllCustomers() {
		return customerRepo.getAllCustomers();
	}
	public boolean deleteCustomer(long customerId) {
		return customerRepo.removeCustomer(customerId);
	}
}
