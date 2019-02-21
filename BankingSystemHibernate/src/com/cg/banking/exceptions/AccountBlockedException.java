package com.cg.banking.exceptions;

public class AccountBlockedException extends Exception{
	public AccountBlockedException() {
		super();
	}
	public AccountBlockedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public AccountBlockedException(String message, Throwable cause) {
		super(message, cause);
	}
	public AccountBlockedException(String message) {
		super(message);
	}
	public AccountBlockedException(Throwable cause) {
		super(cause);
	}
	
}
