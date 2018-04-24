package com.qa.service.repository;

import java.util.List;

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
public class AccountRepository {

	@PersistenceContext(unitName = "primary") // the name we specified in the persistence file
	private EntityManager em;

	@Inject
	private JSONUtil jsonutil;

	@SuppressWarnings("unchecked")
	public String findAllAccounts() {
		Query query = em.createQuery("SELECT a FROM Account a ORDER BY a.firstname DESC", Account.class);
		List<Account> accounts = (List<Account>) query.getResultList();
		return jsonutil.getJSONForObject(accounts);
	}

	public Account findAnAccount(Long id) {

		return em.find(Account.class, id);

	}

	@Transactional(REQUIRED)
	public String deleteAnAccount(Long id) {

		boolean itExists = findAnAccount(id) != null;

		if (itExists) {
			em.remove(em.getReference(Account.class, id));

			return "{\"message\": \"the account has been deleted\"}";
		}

		else {
			return "{\"message\": \"the account doesn't exist so it couldn't be deleted\"}";
		}

	}

	@Transactional(REQUIRED)
	public String createAnAccount(String account) {

		Account newAccount = jsonutil.getObjectForJSON(account, Account.class);

		boolean itExists = findAnAccount(newAccount.getId()) != null;
		if (itExists) {

			return "{\"message\": \"the account exists so it could only be updated\"}";

		}

		else {
			em.persist(newAccount);

			return "{\"message\": \"the account has been added\"}";
		}

	}

	@Transactional(REQUIRED)
	public String updateAnAccount(String account) {

		Account oldaccount = jsonutil.getObjectForJSON(account, Account.class);

		boolean itExists = findAnAccount(oldaccount.getId()) != null;
		if (itExists) {

			em.merge(oldaccount);

			return "{\"message\": \"the account has been updated successfully\"}";

		}

		else {

			return "{\"message\": \"the account doesn't exist so it couldn't be updated\"}";
		}

	}

}
