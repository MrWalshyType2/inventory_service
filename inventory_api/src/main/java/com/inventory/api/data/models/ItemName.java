package com.inventory.api.data.models;

import javax.validation.constraints.NotBlank;

import org.springframework.lang.Nullable;

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

	@Nullable
	private String name;
	
	@NotBlank
	private String alias;
}
