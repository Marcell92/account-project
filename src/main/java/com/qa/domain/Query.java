package com.qa.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

	

import com.qa.domain.Account;
import com.qa.util.JSONUtil;


@Transactional (SUPPORTS)
public class Query {
	
	@PersistenceContext (unitName = "primary")
	private EntityManager em;
	

		public String getAllAccounts() {
        TypedQuery<Account> accounts = em.createQuery("SELECT m FROM Movie m ORDER BY m.title DESC", Account.class);
        	return null;
    }
	
		public Account findAnAccount (Long id) {
		
			return em.find(Account.class, id);
		
	}
	
		public Account deleteAnAccount (Long id) {
		
		Account account = findAnAccount (id);
		
		//boolean uniqueIDexists = account.equals(id);
		
			return em.find(Account.class, id);
		
	}

}
