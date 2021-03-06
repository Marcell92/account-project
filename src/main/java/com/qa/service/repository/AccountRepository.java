package com.qa.service.repository;

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

@Transactional(SUPPORTS)
@Default
public class AccountRepository implements AccountInterface {

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

	@SuppressWarnings("unchecked")
	@Override
	public String findAllAccounts() {
		Query query = em.createQuery("SELECT a FROM Account a ORDER BY a.firstname DESC");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();
		return jsonutil.getJSONForObject(accounts);
	}

	public Account findAnAccount(Long id) {

		return em.find(Account.class, id);

	}

	@Transactional(REQUIRED)
	@Override
	public String deleteAnAccountByAcc(String account) {

		Account existingAccount = jsonutil.getObjectForJSON(account, Account.class);

		boolean itExists = existingAccount != null;

		if (itExists) {
			em.remove(existingAccount);

			return "{\"message\": \"the account has been deleted\"}";
		}

		else {
			return "{\"message\": \"the account doesn't exist so it couldn't be deleted\"}";
		}

	}
	
	@Transactional(REQUIRED)
	@Override
	public String deleteAnAccountByID(Long id) {
		
		Account existingAccount = findAnAccount(id);
		boolean itExists = existingAccount != null;

		if (itExists) {
			em.remove(existingAccount);

			return "{\"message\": \"the account has been deleted\"}";
		}

		else {
			return "{\"message\": \"the account doesn't exist so it couldn't be deleted\"}";
		}

	}

	@Transactional(REQUIRED)
	@Override
	public String createAnAccount(String account) {

		Account newAccount = jsonutil.getObjectForJSON(account, Account.class);

		boolean itExists = findAnAccount(newAccount.getId()) != null;
		if (itExists) {

			return "{\"message\": \"the account exists so it could only be updated but not created\"}";

		}

		else {
			em.persist(newAccount);

			return "{\"message\": \"the account has been successfully added\"}";
		}

	}

	@Transactional(REQUIRED)
	@Override
	public String updateAnAccount(Long id, String account) {

		Account accountToUpdate = jsonutil.getObjectForJSON(account, Account.class);
		Account oldaccount = em.find(Account.class, id);

		boolean itExists = accountToUpdate != null;
		if (itExists) {

			em.merge(oldaccount);

			return "{\"message\": \"the account has been updated successfully\"}";

		}

		else {

			return "{\"message\": \"the account doesn't exist so it couldn't be updated\"}";
		}

	}
	
	public JSONUtil getJsonutil() {
		return jsonutil;
	}

	public void setJsonutil(JSONUtil jsonutil) {
		this.jsonutil = jsonutil;
	}

}
