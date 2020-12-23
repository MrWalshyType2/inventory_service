package com.inventory.api.data.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enumeration of the different Tags for items.
 * 
 * @author morgan
 *
 */
@RequiredArgsConstructor
@Getter
public enum Tag {

	FOOD("Food"),
	DRINK("Drinks"),
	CLOTHES("Clothing"),
	CLOTHING("Clothing"),
	BOOK("Book"),
	BOOKS("Books");
	
	private final String description;
}
