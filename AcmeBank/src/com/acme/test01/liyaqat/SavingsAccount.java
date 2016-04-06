package com.acme.test01.liyaqat;

import com.acme.test01.liyaqat.config.AcmeConfig;
import com.acme.test01.liyaqat.database.SystemDB;
import com.acme.test01.liyaqat.exceptions.AccountNotFoundException;
import com.acme.test01.liyaqat.exceptions.WithdrawalAmountTooLargeException;

public class SavingsAccount extends BankAccount implements AccountService{

	private SystemDB systemDB = SystemDB.getInstance();
	
	@Override
	public void openSavingsAccount(Long accountId, Long amountToDeposit) {
		if(accountId != null) {
			if (amountToDeposit !=null 
					&& amountToDeposit >= AcmeConfig.ACME_BANK_MIN_AMOUNT_TO_OPEN_SAVINGS_ACCOUNT) {
				SavingsAccount savingsAccount = new SavingsAccount();
				savingsAccount.setId(accountId);
				savingsAccount.setBalance(amountToDeposit);
				//add savings account to DB
				systemDB.getBankAccounts().put(savingsAccount.getId(), savingsAccount);
			} else {
				//log(Level.WARNING, "Amount " 
				//		+ amountToDeposit + " is too low. It needs to be: "
				//		+ AcmeConfig.ACME_BANK_MIN_AMOUNT_TO_OPEN_SAVINGS_ACCOUNT);
			}
		} else {
			//log(Level.WARNING, "accountId is null");
		}
	}

	@Override
	public void openCurrentAccount(Long accountId) {
		// Not applicable to the Savings Account
	}

	@Override
	public void withdraw(Long accountId, int amountToWithdraw)
			throws AccountNotFoundException, WithdrawalAmountTooLargeException {
			if (!systemDB.getBankAccounts().containsKey(accountId)) {
				throw new AccountNotFoundException("Cannot find account for account ID " + accountId);
			} 
			
			BankAccount bankAccount = systemDB.getBankAccounts().get(accountId);
			Long balance = bankAccount.getBalance();
			if ((balance - amountToWithdraw) < AcmeConfig.ACME_BANK_MIN_AMOUNT_SAVINGS_BALANCE) {
				throw new WithdrawalAmountTooLargeException("Withdrawal amount is too large, please choose a smaller amount");
			}
			//log(Level.INFO, "Able to Withdraw");
			bankAccount.setBalance(balance - amountToWithdraw);
			
			//write to db updated bankAccount value
			systemDB.getBankAccounts().put(bankAccount.getId(), bankAccount);
	}

	@Override
	public void deposit(Long accountId, int amountToDeposit) throws AccountNotFoundException {
		if (amountToDeposit >= 0) {
			if (!systemDB.getBankAccounts().containsKey(accountId)) {
				throw new AccountNotFoundException("Cannot find account for account ID " + accountId);
			}
			BankAccount bankAccount = systemDB.getBankAccounts().get(accountId);
			bankAccount.setBalance(bankAccount.getBalance() + amountToDeposit);
			// write to db
			systemDB.getBankAccounts().put(bankAccount.getId(), bankAccount);
		} else {
			//log(Level.WARNING, "Attempting to deposit a negative amount");
		}
	}
	


}
