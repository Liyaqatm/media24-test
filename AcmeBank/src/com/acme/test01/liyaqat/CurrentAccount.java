package com.acme.test01.liyaqat;

import com.acme.test01.liyaqat.config.AcmeConfig;
import com.acme.test01.liyaqat.database.SystemDB;
import com.acme.test01.liyaqat.exceptions.AccountNotFoundException;
import com.acme.test01.liyaqat.exceptions.WithdrawalAmountTooLargeException;

public class CurrentAccount extends BankAccount implements AccountService {

	private SystemDB systemDB = SystemDB.getInstance();
	
	@Override
	public void openSavingsAccount(Long accountId, Long amountToDeposit) {
		//Not Applicable
	}

	@Override
	public void openCurrentAccount(Long accountId) {
		CurrentAccount chequeAccount = new CurrentAccount();
		chequeAccount.setId(accountId);
		//add savings account to DB
		systemDB.getBankAccounts().put(chequeAccount.getId(), chequeAccount);
	}

	@Override
	public void withdraw(Long accountId, int amountToWithdraw)
			throws AccountNotFoundException, WithdrawalAmountTooLargeException {
		if (!systemDB.getBankAccounts().containsKey(accountId)) {
			throw new AccountNotFoundException("Cannot find account for account ID " + accountId);
		} 
		BankAccount bankAccount = systemDB.getBankAccounts().get(accountId);
		if (bankAccount.getBalance() != null && bankAccount.getOverdraftAmount() != null) {
			if (bankAccount.getOverdraftAmount() <= AcmeConfig.ACME_BANK_MAX_OVERDRAFT_LIMIT) {
			Long amountAvailable = bankAccount.getBalance() + bankAccount.getOverdraftAmount();
								   
			if (amountToWithdraw > amountAvailable) {
				throw new WithdrawalAmountTooLargeException("Withdrawal amount is too large, please choose a smaller amount");
			}
			//log(Level.INFO, "Able to Withdraw");
			bankAccount.setBalance(bankAccount.getBalance() - amountToWithdraw);
	
			//write to db updated bankAccount value
			systemDB.getBankAccounts().put(bankAccount.getId(), bankAccount);
			} else {
				//limit is too high
			}
		}
	}

	@Override
	public void deposit(Long accountId, int amountToDeposit) throws AccountNotFoundException {
		if (!systemDB.getBankAccounts().containsKey(accountId)) {
			throw new AccountNotFoundException("Cannot find account for account ID " + accountId);
		}
		BankAccount bankAccount = systemDB.getBankAccounts().get(accountId);
		bankAccount.setBalance(bankAccount.getBalance() + amountToDeposit);
		// write to db
		systemDB.getBankAccounts().put(bankAccount.getId(), bankAccount);
	}

}
