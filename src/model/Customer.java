package model;

public class Customer {
	 private long customerId;
	 private String name;
	 private String email;
	 private String phone;
	 private String address;
	 
	 public Customer(long customerId, String name, String email, String phone, String address) {
		 this.customerId=customerId;
		 this.name=name;
		 this.email=email;
		 this.phone=phone;
		 this.address=address;
	 }
	 
	 public long getCustomerId() {
		 return customerId;
	 }
	 public String getName() {
		 return name;
	 }
	 public String getEmail() {
		 return email;
	 }
	 public String getPhone() {
		 return phone;
	 }
	 public String getAddress() {
		 return address;
	 }
	 
	 public void setCustomerId(long customerId) {
		 this.customerId=customerId;
	 }
	 public void setName(String name) {
		 this.name=name;
	 }
	 public void setEmail(String email) {
		 this.email=email;
	 }
	 public void setPhone(String phone) {
		 this.phone=phone;
	 }
	 public void setAddress(String address) {
		 this.address=address;
	 }
	 
	 public String displayCustomer() {
		 return "Customer ID: "+ customerId
				 +" Customer Name: "+name+
				 " Customer Email: "+email+
				 " Customer Phone Number: "+phone+
				 " Customer Address: "+address;
	 }
}
