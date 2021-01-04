package com.inventory.api.service.exception;

public class ItemNotFoundException extends RuntimeException {
	
	public ItemNotFoundException() {
		super();
	}

	public ItemNotFoundException(String string) {
		super(string);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
