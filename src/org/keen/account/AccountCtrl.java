package org.keen.account;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class AccountCtrl {

	static public List<Account> getAccountList() {
		
		return Arrays.asList(
				new Account(LocalDate.parse("2013-12-30"), MoneyDirection.out.toString(), 12.6, "cake"),
				new Account(LocalDate.parse("2013-12-31"), MoneyDirection.in.toString(), 6, "meat")
				);
	}
	
	static public List<AccountLabel> getLabelList(){
		List<Account> account = Arrays.asList(
				new Account(LocalDate.parse("2013-12-30"), MoneyDirection.out.toString(), 12.6, "cake"),
				new Account(LocalDate.parse("2013-12-31"), MoneyDirection.in.toString(), 6, "meat")
				);
		return Arrays.asList(
				new AccountLabel("label1", account),
				new AccountLabel("label2", account)
				);
	}
}
