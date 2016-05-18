package com.unika.hibernatemapping;

public class BeneficiaryDetails implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int customerid;
	private long beneficiaryNumber;
	private String nickName;
	private double limit;

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public long getBeneficiaryNumber() {
		return beneficiaryNumber;
	}

	public void setBeneficiaryNumber(long beneficiaryNumber) {
		this.beneficiaryNumber = beneficiaryNumber;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public double getLimit() {
		return limit;
	}

	public void setLimit(double limit) {
		this.limit = limit;
	}

}
