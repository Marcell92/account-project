package com.qa.service.business;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qa.domain.Account;
import com.qa.service.repository.AccountInterface;
import com.qa.util.JSONUtil;
import com.qa.service.repository.*;

import java.util.Collection;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

public class BusinessImplementation implements AccountInterface {
	
	@PersistenceContext(unitName = "primary") // the name we specified in the persistence file
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Inject
	private JSONUtil jsonutil;
	
	public Account findAnAccount(Long id) {

		return em.find(Account.class, id);

	}

	@Override
	public String findAllAccounts() {
		
		return null;
	}

	@Override
	public String createAnAccount(String account) {
		
		Account newAccount = jsonutil.getObjectForJSON(account, Account.class);

		boolean itExists = findAnAccount(newAccount.getId()) != null;
		
		if (itExists) {

			return "{\"message\": \"the account exists so it could only be updated but not created\"}";

		}
		
		else if (newAccount.getAccountNumber() == "9999") {
			
			return "{\"message\": \"this account is blocked\"}";
			
		}

		else {
			em.persist(newAccount);

			return "{\"message\": \"the account has been successfully added\"}";
		}

		
	}

	@Override
	public String updateAnAccount(Long id, String account) {
		
		return null;
	}

	@Override
	public String deleteAnAccountByAcc(String account) {
		
		return null;
	}

	@Override
	public String deleteAnAccountByID(Long id) {
		
		return null;
	}

}
