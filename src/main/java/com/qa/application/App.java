package com.qa.application;

import com.qa.domain.Account;
import com.qa.service.AccountService;
import com.qa.util.JSONUtil;

public class App {

	public static void main(String[] args) {
		AccountService service = new AccountService();
		JSONUtil util = new JSONUtil();
		Account joeBloggs = new Account(1L, "Joe", "Bloggs", "1234");
		Account janeBloggs = new Account(2L, "Jane", "Bloggs", "1234");
		service.addAccountFromMap(joeBloggs);
		service.addAccountFromMap(janeBloggs);
		String mapAsJSON = util.getJSONForObject(service.getAccountMap());
		System.out.println("This is the account map as JSON " + mapAsJSON);

	}

}
