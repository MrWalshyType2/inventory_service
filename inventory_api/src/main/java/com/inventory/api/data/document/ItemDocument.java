package com.inventory.api.data.document;

import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.inventory.api.data.models.ItemName;
import com.inventory.api.data.models.Price;
import com.inventory.api.data.models.Size;
import com.inventory.api.data.models.Tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class ItemDocument {

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
