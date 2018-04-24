package com.qa.service.repository;

public interface AccountInterface {
	
	String findAllAccounts();

	String createAnAccount(String accout);

	String updateAnAccount(Long id, String account);

	String deleteAnAccount(String account);

}
