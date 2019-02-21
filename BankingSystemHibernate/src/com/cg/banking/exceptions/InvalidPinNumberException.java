package com.cg.banking.exceptions;
public class InvalidPinNumberException extends Exception{
	public InvalidPinNumberException() {
		super();
	}
	public InvalidPinNumberException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public InvalidPinNumberException(String message, Throwable cause) {
		super(message, cause);
	}
	public InvalidPinNumberException(String message) {
		super(message);
	}
	public InvalidPinNumberException(Throwable cause) {
		super(cause);
	}
}
