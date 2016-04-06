package com.acme.test01.liyaqat.exceptions;

/**
 * Checked Exception 
 * @author Liyaqat
 *
 */
public class WithdrawalAmountTooLargeException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public WithdrawalAmountTooLargeException (String message) {
		super("Withdrawal Amount Too Large: " + message);
	}
}
