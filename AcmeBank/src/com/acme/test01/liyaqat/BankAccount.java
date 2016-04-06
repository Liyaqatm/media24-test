package com.acme.test01.liyaqat;

/**
 * Parent Class that Accounts extend
 * @author Liyaqat
 *
 */
public class BankAccount {
	
	private Long id;
	
	private String customerNumber;
	
	private Long balance;
	
	private Long overdraftAmount;
	
	private Long minimumBalance = 0L;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	public Long getOverdraftAmount() {
		return overdraftAmount;
	}
	public void setOverdraftAmount(Long overdraftAmount) {
		this.overdraftAmount = overdraftAmount;
	}
	public Long getMinimumBalance() {
		return minimumBalance;
	}
	public void setMinimumBalance(Long minimumBalance) {
		this.minimumBalance = minimumBalance;
	}

}
