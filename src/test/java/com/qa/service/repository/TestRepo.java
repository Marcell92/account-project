package com.qa.service.repository;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

import org.junit.Test;

@RunWith(MockitoJUnitRunner.class)
public class TestRepo {
	
	@InjectMocks
	private AccountRepository accountrepository;
	
	@Mock
	private EntityManager em;
	
	@Mock
	private Query query;
	
	private JSONUtil jsonutil;
	
	private static final String mockList = "[{\"firstName\":\"Joe\",\"secondName\":\"Bloggs\",\"accountNumber\":\"1234\"}]";
	private static final String mockObject = "{\"firstName\":\"Joe\",\"secondName\":\"Bloggs\",\"accountNumber\":\"1234\"}";
	
	@Before
	
	public void pre() {	
		
		accountrepository.setEm(em);
		jsonutil = new JSONUtil();
		accountrepository.setJsonutil(jsonutil);
		
	}

	@Test
	public void testFindAllAccounts() {
		
		Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(query);
		List<Account> accounts = new ArrayList<Account>();
		accounts.add(new Account("Joe", "Bloggs", "1234"));
		Mockito.when(query.getResultList()).thenReturn(accounts);
		assertEquals(mockList, accountrepository.findAllAccounts());
		
	}
	
	@Test
	public void testCreateAccount() {
		String expectedAnswer = accountrepository.createAnAccount(1L, mockObject);
		assertEquals(expectedAnswer, "{\"message\": \"the account has been successfully added\"}");
	}

	@Test
	public void testUpdateAccount() {
		String expectedAnswer = accountrepository.updateAnAccount(1L, mockObject);
		assertEquals(expectedAnswer, "{\"message\": \"the account has been updated successfully\"}");
	}
	
	@Test
	public void testUpdateAccountFail() {
		String expectedAnswer = accountrepository.updateAnAccount(1L, null);
		assertEquals(expectedAnswer, "{\"message\": \"the account doesn't exist so it couldn't be updated\"}");
	}

	@Test
	public void testDeleteAccountFail() {
		String expectedAnswer = accountrepository.deleteAnAccount(null);
		assertEquals(expectedAnswer, "{\"message\": \"the account doesn't exist so it couldn't be deleted\"}");
	}
	
	@Test
	public void testDeleteAccount() {
		String expectedAnswer = accountrepository.deleteAnAccount(mockObject);
		assertEquals(expectedAnswer, "{\"message\": \"the account has been deleted\"}");
	}
	
}
