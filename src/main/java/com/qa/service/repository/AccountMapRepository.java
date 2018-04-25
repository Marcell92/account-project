package com.qa.service.repository;

import java.util.Map;

import static javax.transaction.Transactional.TxType.REQUIRED;

import java.util.HashMap;
import javax.enterprise.context.ApplicationScoped;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@ApplicationScoped
@Alternative
public class AccountMapRepository implements AccountInterface {

	@Inject
	private JSONUtil jsonutil;

	private Map<Long, Account> AccountMap = new HashMap<Long, Account>();
	private Long id = 1L;

	public AccountMapRepository() {

		this.AccountMap = AccountMap;
		this.id = id;
		initAccountMap();
	}

	@Override
	public String findAllAccounts() {

		return jsonutil.getJSONForObject(AccountMap.values());
	}
	
	@Transactional(REQUIRED)
	@Override
	public String createAnAccount(String account) {

		id++;
		Account newAccount = jsonutil.getObjectForJSON(account, Account.class);

		boolean itExists = AccountMap.containsKey(newAccount.getId());
		if (itExists) {

			return "{\"message\": \"the account exists so it could only be updated but not created\"}";

		}

		else {
			AccountMap.put(id, newAccount);

			return account + ": this account has been added";
		}

	}
	@Transactional(REQUIRED)
	@Override
	public String updateAnAccount(Long id, String account) {

		Account accountToUpdate = jsonutil.getObjectForJSON(account, Account.class);

		boolean itExists = accountToUpdate != null;
		if (itExists) {

			AccountMap.put(id, accountToUpdate);

			return account + ": this account has been updated";

		}

		else {

			return "{\"message\": \"the account doesn't exist so it couldn't be updated\"}";
		}

	}

	@Transactional(REQUIRED)
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public String deleteAnAccountByAcc(String account) {
		
		AccountMap.remove(account);
		return account + ": this account has been successfully removed";
	}
	
	@Transactional(REQUIRED)
	@Override
	public String deleteAnAccountByID(Long id) {
		
		AccountMap.remove(1L);
		return "{\"message\": \"the account has been deleted\"}";
	}
	
	private void initAccountMap() {
		Account account = new Account("Joe", "Bloggs", "1234");
		AccountMap.put(1L, account);
	}

}
