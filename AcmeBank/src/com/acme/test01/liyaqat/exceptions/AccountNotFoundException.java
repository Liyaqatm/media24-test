package com.acme.test01.liyaqat.exceptions;

/**
 * Un-Checked Exception 
 * @author Liyaqat
 *
 */
public class AccountNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;

	public AccountNotFoundException (String message) {
		super("Account Not Found: " + message);
	}
}
