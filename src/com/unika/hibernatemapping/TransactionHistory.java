package com.unika.hibernatemapping;

public class TransactionHistory {
	private int txnid;
	private long withaccountnumber;
	private long toaccountnumber;
	private double amount;
	private String withtransactioncode;
	private String totransactioncode;
	private String timestamp;

	public int getTxnid() {
		return txnid;
	}

	public void setTxnid(int txnid) {
		this.txnid = txnid;
	}

	public long getWithaccountnumber() {
		return withaccountnumber;
	}

	public void setWithaccountnumber(long withaccountnumber) {
		this.withaccountnumber = withaccountnumber;
	}

	public long getToaccountnumber() {
		return toaccountnumber;
	}

	public void setToaccountnumber(long toaccountnumber) {
		this.toaccountnumber = toaccountnumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getWithtransactioncode() {
		return withtransactioncode;
	}

	public void setWithtransactioncode(String withtransactioncode) {
		this.withtransactioncode = withtransactioncode;
	}

	public String getTotransactioncode() {
		return totransactioncode;
	}

	public void setTotransactioncode(String totransactioncode) {
		this.totransactioncode = totransactioncode;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}