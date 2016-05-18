package com.unika.login;

import com.unika.hibernatemapping.RegisteredUserDetails;

public interface LoginDAO {
	public void saveOrUpdate(RegisteredUserDetails logindb);

	public int loginAuthentication(int customerid, String password);

	public String getName(int customerid);
	
	public RegisteredUserDetails getSecutityQuestions(int customerid);
}
