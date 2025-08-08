package com.authservcie.authservice.exceptionhandler;

public class EmailAlreadyExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmailAlreadyExistException(String ex) {
		super(ex);
	}

}
