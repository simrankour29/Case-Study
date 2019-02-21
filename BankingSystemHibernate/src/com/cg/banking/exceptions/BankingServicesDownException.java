package com.cg.banking.exceptions;

public class BankingServicesDownException extends Exception{
	public BankingServicesDownException() {
		super();
	}
	public BankingServicesDownException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public BankingServicesDownException(String message, Throwable cause) {
		super(message, cause);
	}
	public BankingServicesDownException(String message) {
		super(message);
	}
	public BankingServicesDownException(Throwable cause) {
		super(cause);
	}

}
