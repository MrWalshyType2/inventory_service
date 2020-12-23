package com.inventory.api.data.models;

import lombok.Value;

/**
 * Name of an item, also has an alias.
 * 
 * IMMUTABLE
 * 
 * @author morgan
 *
 */
@Value
public class ItemName {

	private String name;
	private String alias;
}
