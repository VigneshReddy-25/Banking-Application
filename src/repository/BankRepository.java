package repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Bank;

public class BankRepository {

	private List<Bank> banks = new ArrayList<>();
	
	public boolean addBank(Bank bank) {
		Iterator<Bank> itr=banks.iterator();
		while(itr.hasNext()) {
			Bank bank1=itr.next();
			if(bank.getBankCode().equals(bank1.getBankCode())) {
				return false;
			}
		}
		banks.add(bank);
		return true;
	}
	
	public Bank findBank(String bankCode) {
		Iterator<Bank> itr=banks.iterator();
		while(itr.hasNext()) {
			Bank bank1=itr.next();
			if(bankCode.equals(bank1.getBankCode())) {
				return bank1;
			}
		}
		return null;
	}
	
	public boolean removeBank(String bankCode) {
		Iterator<Bank> itr=banks.iterator();
		while(itr.hasNext()) {
			Bank bank1=itr.next();
			if(bankCode.equals(bank1.getBankCode())) {
				itr.remove();
				return true;
			}
		}
		return false;
	}
	
	public List<Bank> getAllBanks(){
		return new ArrayList<>(banks);
	}
}
