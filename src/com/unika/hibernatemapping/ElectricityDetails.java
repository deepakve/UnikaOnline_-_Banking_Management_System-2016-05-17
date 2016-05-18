package com.unika.hibernatemapping;

import java.io.Serializable;

public class ElectricityDetails implements Serializable {
	private static final long serialVersionUID = 1L;
	private int areaCode;
	private int serviceCode;
	private float amount;

	public int getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}

	public int getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(int serviceCode) {
		this.serviceCode = serviceCode;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
}
