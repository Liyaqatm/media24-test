package com.acme.test01.liyaqat.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.acme.test01.liyaqat.BankAccount;
import com.acme.test01.liyaqat.CurrentAccount;
import com.acme.test01.liyaqat.SavingsAccount;

/**
 * An in memory database created using the Singleton Pattern.
 * A point to note is concurrency
 * @author Liyaqat
 *
 */
public class SystemDB {
	
	private static SystemDB systemDB = null;
	private static Map<Long, BankAccount> bankAccounts = new HashMap<Long, BankAccount>();
	

	public static Map<Long, BankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public static void setBankAccounts(Map<Long, BankAccount> bankAccounts) {
		SystemDB.bankAccounts = bankAccounts;
	}

	private SystemDB() {
	}
	
	public static SystemDB getInstance() {
		synchronized (SystemDB.class) {
			if (systemDB == null) {
				systemDB = new SystemDB();
				initData();
			}
		}
		return systemDB;
	}
	
	
	/**
	 * This method initializes the test data
	 */
	private static void initData() {
		SavingsAccount savingsAccountOne = new SavingsAccount();
		savingsAccountOne.setId(1L);
		savingsAccountOne.setBalance(2000L);
		
		SavingsAccount savingsAccountTwo = new SavingsAccount();
		savingsAccountTwo.setId(2L);
		savingsAccountTwo.setBalance(5000L);
		
		CurrentAccount chequeAccountOne = new CurrentAccount();
		chequeAccountOne.setId(3L);
		chequeAccountOne.setBalance(1000L);
		chequeAccountOne.setOverdraftAmount(10000L);
		
		CurrentAccount chequeAccountTwo = new CurrentAccount();
		chequeAccountTwo.setId(4L);
		chequeAccountTwo.setBalance(-1000L);
		chequeAccountTwo.setOverdraftAmount(20000L);
				
		bankAccounts.put(1L, savingsAccountOne);
		bankAccounts.put(2L, savingsAccountTwo);
		bankAccounts.put(3L, chequeAccountOne);
		bankAccounts.put(4L, chequeAccountTwo);
	}
}
