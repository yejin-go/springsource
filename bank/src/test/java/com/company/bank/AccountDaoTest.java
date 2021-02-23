package com.company.bank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AccountDaoTest {

	private static final String EXISTING_ACCOUNT_NO = "1234";
	private static final String NEW_ACCOUNT_NO = "5678";
	
	private Account existingAccount;
	private Account newAccount;
	private AccountDaoImpl accountDao;
	
	@Before
	public void init() {
		existingAccount = new Account(EXISTING_ACCOUNT_NO, 100);
		newAccount = new Account(NEW_ACCOUNT_NO, 200);
		accountDao = new AccountDaoImpl();
		accountDao.createAccount(existingAccount);
	}
	
	@Test
	public void accountExist() {
		assertTrue(accountDao.accountExistst(EXISTING_ACCOUNT_NO));
		assertFalse(accountDao.accountExistst(NEW_ACCOUNT_NO));
	}
	
	@Test
	public void createAccount() {
		accountDao.createAccount(newAccount);
		assertEquals(accountDao.findAccount(NEW_ACCOUNT_NO),newAccount);
	}
	
	@Test(expected = DuplicateAccountException.class)
	public void createDuplicationAccount() {
		accountDao.createAccount(existingAccount);
	}
	
	@Test
	public void updateExistedAccount() {
		existingAccount.setBalance(150);
		accountDao.updateAccount(existingAccount);
		assertEquals(accountDao.findAccount(EXISTING_ACCOUNT_NO), existingAccount);
	}
	
	@Test(expected = AccountNotFoundException.class)
	public void updateNotExistedAccount() {
		accountDao.updateAccount(newAccount);
	}
	
	@Test
	public void removeExistedAccount() {
		accountDao.removeAccount(existingAccount);
		assertFalse(accountDao.accountExistst(EXISTING_ACCOUNT_NO));
	}
	
	@Test(expected = AccountNotFoundException.class)
	public void removeNotExistedAccount() {
		accountDao.removeAccount(newAccount);	
	}
	
	@Test
	public void findExistedAccount() {
		Account account = accountDao.findAccount(EXISTING_ACCOUNT_NO);
		assertEquals(account, existingAccount);
	}
	
	@Test(expected = AccountNotFoundException.class)
	public void findNotExistedAccount() {
		accountDao.findAccount(NEW_ACCOUNT_NO);
	}
	
}
