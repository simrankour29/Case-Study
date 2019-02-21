package com.cg.banking.exceptions;
public class InvalidAccountTypeException extends Exception{
	public InvalidAccountTypeException() {
		super();
	}
	public InvalidAccountTypeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public InvalidAccountTypeException(String message, Throwable cause) {
		super(message, cause);
	}
	public InvalidAccountTypeException(String message) {
		super(message);
	}
	public InvalidAccountTypeException(Throwable cause) {
		super(cause);
	}
}
