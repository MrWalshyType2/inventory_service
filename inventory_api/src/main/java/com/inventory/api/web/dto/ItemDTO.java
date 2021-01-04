package com.inventory.api.web.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.lang.Nullable;

import com.inventory.api.data.domain.Item;
import com.inventory.api.data.models.ItemName;
import com.inventory.api.data.models.Price;
import com.inventory.api.data.models.Size;
import com.inventory.api.data.models.Tag;

import lombok.Value;

@Value
public class ItemDTO {

	public ItemDTO(Item item) {
		this(item.getId(), item.getName(), item.getDescription(), item.getPrice(), item.getStock(), item.getSize(), item.getTags(), item.getItemCode());
	}

	public ItemDTO(String id, ItemName name, String description, Price price, Integer stock, Size size,
			Set<Tag> tags, String itemCode) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.size = size;
		this.tags = tags;
		this.itemCode = itemCode;
	}

	@Null
	private String id;
	
	@NotNull
	private ItemName name;
	
	@NotBlank
	private String description;
	
	@NotNull
	private Price price;
	
	@PositiveOrZero
	private Integer stock;
	
	@NotNull
	private Size size;
	
	@Nullable
	private Set<Tag> tags;
	
	@NotNull
	private String itemCode;
}
