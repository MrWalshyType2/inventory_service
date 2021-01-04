package com.inventory.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.api.data.domain.Item;
import com.inventory.api.data.domain.document.ItemDocument;
import com.inventory.api.data.models.ItemName;
import com.inventory.api.data.repository.ItemRepository;
import com.inventory.api.web.dto.ItemDTO;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepository;

	@Override
	public List<Item> getAllItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getItemById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getItemByItemName(ItemName itemName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getItemByItemAlias(String alias) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item postNewItem(ItemDTO itemDTO) {
		ItemDocument savedItemDoc = itemRepository.save(new ItemDocument(itemDTO));
		
		return new Item(savedItemDoc);
	}

	@Override
	public Item putUpdateItem(ItemDTO itemDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteItemById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
