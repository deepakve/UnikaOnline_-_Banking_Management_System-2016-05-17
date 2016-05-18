package com.unika.accounts;

public class AccountsServiceImpl implements AccountsService {

	@Override
	public String convertAccountNumber(long accountNo) {
		String converted = "";
		converted = "" + accountNo % 10;
		accountNo = accountNo / 10;
		converted = accountNo % 10 + converted;
		accountNo = accountNo / 10;
		converted = accountNo % 10 + converted;
		accountNo = accountNo / 10;
		while (accountNo != 0) {
			accountNo = accountNo / 10;
			converted = "X" + converted;
		}

		if (converted.length() <= 12) {
			int differ = 12 - converted.length();
			for (int i = 0; i < differ; i++)
				converted = "X" + converted;
		}
		return converted;
	}
}
