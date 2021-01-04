package com.inventory.api.data.domain;

import java.util.Set;

import com.inventory.api.data.domain.document.ItemDocument;
import com.inventory.api.data.models.ItemName;
import com.inventory.api.data.models.Price;
import com.inventory.api.data.models.Size;
import com.inventory.api.data.models.Tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * Domain object to represent an Item from a DTO or Document access.
 * 
 * @author morgan
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

	public Item(ItemDocument savedItemDoc) {
		this.id = savedItemDoc.getId();
		this.name = savedItemDoc.getName();
		this.description = savedItemDoc.getDescription();
		this.price = savedItemDoc.getPrice();
		this.stock = savedItemDoc.getStock();
		this.size = savedItemDoc.getSize();
		this.tags = savedItemDoc.getTags();
		this.itemCode = savedItemDoc.getItemCode();
	}

	private String id;
	
	private ItemName name;
	
	private String description;
	
	private Price price;
	
	private Integer stock;
	
	private Size size;
	
	private Set<Tag> tags;
	
	private String itemCode;
}
