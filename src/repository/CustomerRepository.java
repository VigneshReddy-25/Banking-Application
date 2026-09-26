package repository;

import java.util.ArrayList;
import java.util.Iterator;

import model.Customer;

public class CustomerRepository {

	private ArrayList<Customer> customers=new ArrayList<>();
	
	public boolean addCustomer(Customer customer) {
		Iterator<Customer> ite1=customers.iterator();
		while(ite1.hasNext()) {
			Customer ch1=ite1.next();
			if(ch1.getCustomerId()==customer.getCustomerId()) {
				return false;
			}
		}
		customers.add(customer);
		return true;
	}
	public Customer findCustomer(long customerId) {
		Iterator<Customer> ite1=customers.iterator();
		while(ite1.hasNext()) {
			Customer ch1=ite1.next();
			if(ch1.getCustomerId()==customerId) {
				return ch1;
			}
		}
		return null;
	}
	public boolean removeCustomer(long customerId) {
		Iterator<Customer> ite1=customers.iterator();
		while(ite1.hasNext()) {
			Customer ch1=ite1.next();
			if(ch1.getCustomerId()==customerId) {
				ite1.remove();
				return true;
			}
		}
		return false;
	}
	public ArrayList<Customer> getAllCustomers() {
		return new ArrayList<>(customers);
	}
}
