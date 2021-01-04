package com.inventory.api.service;

import java.util.List;

import com.inventory.api.data.domain.Item;
import com.inventory.api.data.models.ItemName;
import com.inventory.api.web.dto.ItemDTO;

public interface ItemService {

	List<Item> getAllItems();
	
	Item getItemById(String id);
	
	Item getItemByItemName(ItemName itemName);
	
	Item getItemByItemAlias(String alias);
	
	Item postNewItem(ItemDTO itemDTO);
	
	Item putUpdateItem(ItemDTO itemDTO, String id);
	
	String deleteItemById(String id);
}
