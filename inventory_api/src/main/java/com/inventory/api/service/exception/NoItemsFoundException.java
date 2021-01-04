package com.inventory.api.service.exception;

public class NoItemsFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoItemsFoundException() {
		super();
	}

	public NoItemsFoundException(String message) {
		super(message);
	}
}
