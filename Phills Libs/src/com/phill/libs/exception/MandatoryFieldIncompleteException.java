package com.phill.libs.exception;

public class MandatoryFieldIncompleteException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public MandatoryFieldIncompleteException(String message) {
		super(message);
	}

}
