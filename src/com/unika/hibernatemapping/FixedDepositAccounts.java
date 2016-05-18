package com.unika.hibernatemapping;

import java.io.Serializable;

public class FixedDepositAccounts implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int customerid;
	private long accountNumber;
	private double amount;
	private String depositTypeCode;
	private String createdon;

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDepositTypeCode() {
		return depositTypeCode;
	}

	public void setDepositTypeCode(String depositCode) {
		this.depositTypeCode = depositCode;
	}

	public String getCreatedon() {
		return createdon;
	}

	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}
}
