package com.unika.admin.create;

import com.unika.hibernatemapping.CustomerDetails;

public interface CustomerDetailsDAO {

	public void createCustomerDetails(CustomerDetails cd);
	public CustomerDetails getCustomerDetails(int customerid);
}
