package com.inventory.api.data.domain.document;

import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.inventory.api.data.models.ItemName;
import com.inventory.api.data.models.Price;
import com.inventory.api.data.models.Size;
import com.inventory.api.data.models.Tag;
import com.inventory.api.web.dto.ItemDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class ItemDocument {

	public ItemDocument(ItemDTO itemDTO) {
		this.name = itemDTO.getName();
		this.description = itemDTO.getDescription();
		this.price = itemDTO.getPrice();
		this.stock = itemDTO.getStock();
		this.size = itemDTO.getSize();
		this.tags = itemDTO.getTags();
		this.itemCode = itemDTO.getItemCode();
	}

	@MongoId
	private String id;
	
	@Field
	private ItemName name;
	
	@Field
	private String description;
	
	@Field
	private Price price;
	
	@Field
	private Integer stock;
	
	@Field
	private Size size;
	
	@Field
	private Set<Tag> tags;
	
	@Field
	private String itemCode;
}
