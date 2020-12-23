package com.inventory.api.data.domain;

import java.util.Set;

import com.inventory.api.data.models.ItemName;
import com.inventory.api.data.models.Price;
import com.inventory.api.data.models.Size;
import com.inventory.api.data.models.Tag;

import lombok.Value;

/**
 * Immutable domain object to represent an Item from a DTO or Document access.
 * 
 * @author morgan
 *
 */
@Value
public class Item {

	private String id;
	
	private ItemName name;
	
	private String description;
	
	private Price price;
	
	private Integer stock;
	
	private Size size;
	
	private Set<Tag> tags;
	
	private String itemCode;
}
