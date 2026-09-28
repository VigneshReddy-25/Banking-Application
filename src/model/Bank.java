package model;

public class Bank {

	private String bankName;
	private String bankCode;
	
	public Bank(String bankName,String bankCode) {
		this.bankName=bankName;
		this.bankCode=bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public String getBankCode() {
		return bankCode;
	}
	
	
	public void setBankName(String bankName) {
		this.bankName=bankName;
	}
	public void setBankCode(String bankCode) {
		this.bankCode=bankCode;
	}
	
	public String displayBank() {
		return "Bank Name: "+bankName
				+" Bank Code: "+bankCode;
	}
}
