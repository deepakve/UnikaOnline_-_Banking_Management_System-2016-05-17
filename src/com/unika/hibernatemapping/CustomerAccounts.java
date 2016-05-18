package com.unika.hibernatemapping;

import java.io.Serializable;

public class CustomerAccounts implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int customerid;
	private long accountnumber;
	private int accountcode;
	private String branchcode;
	private double balance;

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public long getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(long accountnumber) {
		this.accountnumber = accountnumber;
	}

	public int getAccountcode() {
		return accountcode;
	}

	public void setAccountcode(int accountcode) {
		this.accountcode = accountcode;
	}

	public String getBranchcode() {
		return branchcode;
	}

	public void setBranchcode(String branchcode) {
		this.branchcode = branchcode;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}