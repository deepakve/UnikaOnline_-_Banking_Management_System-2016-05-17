package com.unika.transactions;

import java.util.ArrayList;

import com.unika.hibernatemapping.TransactionHistory;

public interface TransactionDAO {
	public boolean transferFunds(int customerid, long withAccountNumber,
			long toAccountNumber, double amount);

	public ArrayList<TransactionHistory> miniStatement(long accountnumber);
	
	public String getBranchDetails(int branchCode);
	
	public String getBenficiaryName(int customerid, long beneficiaryNumber);
	
	public boolean passwordAuthentication(int customerid, String passwordConformation);
	
	public long getAccountNumber(int customerid);
}
