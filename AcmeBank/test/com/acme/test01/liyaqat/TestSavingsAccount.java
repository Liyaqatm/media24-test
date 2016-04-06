package com.acme.test01.liyaqat;

import org.junit.Assert;
import org.junit.Test;

import com.acme.test01.liyaqat.exceptions.AccountNotFoundException;
import com.acme.test01.liyaqat.exceptions.WithdrawalAmountTooLargeException;


public class TestSavingsAccount {

	@Test
	public void testOpenSavingsAccount(){
		SavingsAccount savingsAccount  = new SavingsAccount();
		savingsAccount.openSavingsAccount(7L, 1000L);
		//methods do not return anything
	}
	
	@Test
	public void testOpenSavingsAccountWithMinAmount(){
		SavingsAccount savingsAccount  = new SavingsAccount();
		savingsAccount.openSavingsAccount(7L, 10L);
		//methods do not return anything
	}
	
	@Test
	public void testWithdraw() throws WithdrawalAmountTooLargeException, AccountNotFoundException {
		SavingsAccount savingsAccount  = new SavingsAccount();
		savingsAccount.withdraw(1L, 500);
	}
	
	@Test
	public void testWithdrawTooHigh() throws WithdrawalAmountTooLargeException, AccountNotFoundException {
		SavingsAccount savingsAccount  = new SavingsAccount();
		try {
			savingsAccount.withdraw(1L, 5000);
		} catch (WithdrawalAmountTooLargeException withdrawalAmountTooLargeException) {
			// expected
			return;
		}
		Assert.fail("Expected to throw a WithrdrawlAmountTooLargeException");
	}
	
	@Test
	public void testWithdrawFromNonExistentAccount() throws WithdrawalAmountTooLargeException {
		SavingsAccount savingsAccount  = new SavingsAccount();
		try {
			savingsAccount.withdraw(10L, 5000);
		} catch (AccountNotFoundException accountNotFoundException) {
			// expected
			return;
		}
		Assert.fail("Expected to throw a AccountNotFoundException");
	}
	
	@Test
	public void testDeposit() throws AccountNotFoundException {
		SavingsAccount savingsAccount  = new SavingsAccount();
		savingsAccount.deposit(1L, 500);
	}
	
	@Test
	public void testDepositIntoNonExistentAccount() throws AccountNotFoundException {
		SavingsAccount savingsAccount  = new SavingsAccount();
		try {
			savingsAccount.deposit(100L, 500);
		} catch (AccountNotFoundException accountNotFoundException) {
			// expected
			return;
		}
		Assert.fail("Expected to throw a AccountNotFoundException");
	}
}
