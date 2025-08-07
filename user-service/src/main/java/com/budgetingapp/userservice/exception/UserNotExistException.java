package com.budgetingapp.userservice.exception;

public class UserNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserNotExistException(String ex) {
		super(ex);
	}

}
