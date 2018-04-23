package com.qa.service.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import com.qa.domain.Account;

@Transactional(SUPPORTS)
public class AccountRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	public List<Account> findAllAccounts() {
		TypedQuery<Account> query = em.createQuery("SELECT a FROM Account a ORDER BY a.firstname DESC", Account.class);
		return query.getResultList();
	}

	public Account findAnAccount(Long id) {

		return em.find(Account.class, id);

	}

	@Transactional(REQUIRED)
	public void deleteAnAccount(Long id) {

		boolean itExists = em.getReference(Account.class, id) != null;

		if (itExists) {
			em.remove(em.getReference(Account.class, id));

		}

	}

	@Transactional(REQUIRED)
	public String updateAnAccount(Long id) {
		
		boolean itExists = em.getReference(Account.class, id) != null;
		if (itExists) {
			
		}

		return null;

	}

}
