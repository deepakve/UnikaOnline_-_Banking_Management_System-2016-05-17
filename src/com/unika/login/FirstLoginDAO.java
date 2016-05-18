package com.unika.login;

import javax.servlet.http.HttpServletRequest;

import com.unika.hibernatemapping.RegisteredUserDetails;

public interface FirstLoginDAO {
	public boolean saveNecessaryDetails(int customerid, String newPassword,
			String secQues1, String answer1, String secQues2, String answer2);

	public void saveRegAcc(RegisteredUserDetails rud,
			HttpServletRequest request, String c);

	public String randomPasswordGenerator();
}
