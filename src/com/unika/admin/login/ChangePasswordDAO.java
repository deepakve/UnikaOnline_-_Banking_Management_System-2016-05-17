package com.unika.admin.login;

public interface ChangePasswordDAO {

	public boolean changeAdminPassword(String adminid, String oldpassword, String newpassword);
}
