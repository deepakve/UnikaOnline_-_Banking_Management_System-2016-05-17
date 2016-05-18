package com.unika.accounts.fixed;

import java.util.ArrayList;

import com.unika.hibernatemapping.FixedDepositAccounts;
import com.unika.hibernatemapping.FixedDeposits;

public interface FixedDepositsDAO {
	public boolean addFixedDeposit(int customerid, long SavingAccountNumber,
			String depositCode, double amount);

	public boolean cancelFixedDeposit(int customerid, long depositNumber);

	public ArrayList<FixedDeposits> getDepositPolicies();

	public ArrayList<FixedDepositAccounts> viewFixedDeposits(int customerid);

	public int getDaysToClose(int customerid, long accno);

	public String maturityDateCalculator(int days);

	public FixedDepositAccounts getSelectedDepositDetails(int customerid,
			long depositNumber);
}
