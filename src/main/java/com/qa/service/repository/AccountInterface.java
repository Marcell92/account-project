package com.qa.service.repository;

import com.qa.domain.Account;

public interface AccountInterface {
	
	String findAllAccounts();

	String createAnAccount(String account);

	String updateAnAccount(Long id, String account);

	String deleteAnAccountByAcc(String account);

	String deleteAnAccountByID(Long id);

}
