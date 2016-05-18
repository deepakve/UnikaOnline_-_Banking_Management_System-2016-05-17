package com.unika.billpay;

public interface BillPayDAO {
	public String electricityBillAmount(int areaCode, int serviceNumber);

	public boolean electricityBillPay(int customerid, int areaCode,
			int serviceNumber);

	public String moblieBillAmount(long phNumber);
	
	public boolean mobileBillPay(int customerid, long phNumber);
}
