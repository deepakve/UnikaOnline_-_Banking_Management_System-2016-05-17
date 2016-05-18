package com.unika.accounts;

import java.util.ArrayList;

import com.unika.hibernatemapping.CustomerAccounts;
import com.unika.hibernatemapping.CustomerDetails;
import com.unika.hibernatemapping.BeneficiaryDetails;
import com.unika.hibernatemapping.RegisteredUserDetails;

public interface AccountsDAO {
	public ArrayList<CustomerAccounts> getAccountsInformation(int customerid);
	
	public ArrayList<CustomerAccounts> getSelectedAccountsInformation(int customerid, String selected);

	public boolean addBeneficiary(int customerid, long beneficiaryNumber,
			String nickName, double limit);

	public boolean deleteBeneficiary(int customerid, long beneficiaryNumber);

	public ArrayList<BeneficiaryDetails> viewBeneficiary(int customerid);
	
	public ArrayList<BeneficiaryDetails> getSelectedBeneficiary(int customerid, String selected);

	public CustomerDetails getCustomerDetails(int customerid);

	public RegisteredUserDetails getContactDetails(int customerid);

	public boolean editContactDetails(int customerid, String phoneNumber,
			String emailid);

	public boolean modifyBeneficiary(int customerid, long beneficiaryNumber, double limit, String name);
	
	public boolean checkBeneficiaryAvaliability(long beneficiary);
	
	public boolean editPersonalDetails(int customerid, String fname, String email, String lname, String mobile);
	
	public CustomerAccounts getCustomerAccounts(int customerid);
	
	public long getSavingsAccountNumber(int customerid);
}
