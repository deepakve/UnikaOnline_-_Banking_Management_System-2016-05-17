package com.unika.login;

public interface ChangePasswordDAO {
	public boolean changePassword(int customerid, String oldPassword,
			String newPassword);
}
