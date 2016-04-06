package com.acme.test01.liyaqat;

import com.acme.test01.liyaqat.exceptions.AccountNotFoundException;
import com.acme.test01.liyaqat.exceptions.WithdrawalAmountTooLargeException;

/**
 * This interface defines the services that the bank accounts Implement.
 * 
 * @author Liyaqat
 *
 */
public interface AccountService {

	public void openSavingsAccount(Long accountId, Long amountToDeposit);

	public void openCurrentAccount(Long accountId);

	public void withdraw(Long accountId, int amountToWithdraw)
			throws AccountNotFoundException, WithdrawalAmountTooLargeException;

	public void deposit(Long accountId, int amountToDeposit) throws AccountNotFoundException;
}
