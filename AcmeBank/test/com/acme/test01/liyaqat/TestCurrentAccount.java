package com.acme.test01.liyaqat;

import static org.junit.Assert.*;

import org.junit.*;

import com.acme.test01.liyaqat.exceptions.AccountNotFoundException;
import com.acme.test01.liyaqat.exceptions.WithdrawalAmountTooLargeException;


public class TestCurrentAccount {

	@Test
	public void testWithdraw() throws WithdrawalAmountTooLargeException, AccountNotFoundException {
		CurrentAccount currentAccount = new CurrentAccount();
		currentAccount.withdraw(3L, 100);
	}
	
	@Test
	public void testWithdrawTooHigh() throws WithdrawalAmountTooLargeException, AccountNotFoundException {
		CurrentAccount currentAccount = new CurrentAccount();
		try {
			currentAccount.withdraw(3L, 100000);
		} catch (WithdrawalAmountTooLargeException withdrawalAmountTooLargeException) {
			// expected
			return;
		}
		Assert.fail("Expected to throw a WithdrawalAmountTooLargeException");
	}
	
	@Test
	public void testWithdrawFromInvalidAccount() {
		CurrentAccount currentAccount = new CurrentAccount();
		try {
			currentAccount.withdraw(30L, 100);
		} catch (AccountNotFoundException accountNotFoundException) {
			// expected
			return;
		}
		Assert.fail("Expected to throw a AccountNotFoundException");
	}
	
	@Test
	public void testOpenCurrentAccount(){
		CurrentAccount currentAccount  = new CurrentAccount();
		currentAccount.openCurrentAccount(7L);
		//methods do not return anything
	}
	
}
