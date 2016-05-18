package com.unika.login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordConversion {
	public String getConvertedPassword(String password){
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}

		return sb.toString();
	}
}
