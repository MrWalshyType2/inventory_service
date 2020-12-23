package com.inventory.api.data.models;

import lombok.Value;

/**
 * Represents the price of an item, with a String and a Double representation.
 * 
 * @author morga
 *
 */
@Value
public class Price {

	/**
	 * Precise price as a String
	 */
	private String strPrice;
	
	/**
	 * Approximate price as a Double
	 */
	private Double price;
}
