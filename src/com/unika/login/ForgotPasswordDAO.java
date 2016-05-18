package com.unika.login;

public interface ForgotPasswordDAO {
	public boolean getSecurityQuestions(int customerid, String contact,
			String email);

	public boolean securityAnswerAuthentication(int customerid,
			String answer1, String answer2);

	public boolean resetPassword(int customerid, String newPassword);
}
