package com.inventory.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.api.data.domain.Item;
import com.inventory.api.data.domain.document.ItemDocument;
import com.inventory.api.data.models.ItemName;
import com.inventory.api.data.repository.ItemRepository;
import com.inventory.api.service.exception.ItemNotFoundException;
import com.inventory.api.web.dto.ItemDTO;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepository;

	@Override
	public List<Item> getAllItems() {
		return itemRepository.findAll()
							 .stream()
							 .map(itemDoc -> new Item(itemDoc))
							 .collect(Collectors.toList());
	}

	@Override
	public Item getItemById(String id) {
		return itemRepository.findById(id)
							 .map(itemDoc -> new Item(itemDoc))
							 .orElseThrow(ItemNotFoundException::new);
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
	public Item putUpdateItem(ItemDTO itemDTO, String id) {
		Item itemToUpdate = getItemById(id);
		
		itemToUpdate.setDescription(itemDTO.getDescription());
		itemToUpdate.setName(itemDTO.getName());
		itemToUpdate.setPrice(itemDTO.getPrice());
		itemToUpdate.setSize(itemDTO.getSize());
		itemToUpdate.setItemCode(itemDTO.getItemCode());
		itemToUpdate.setStock(itemDTO.getStock());
		itemToUpdate.setTags(itemDTO.getTags());
		
		ItemDocument updatedItem = itemRepository.save(new ItemDocument(itemToUpdate));
		
		return new Item(updatedItem);
	}

	@Override
	public String deleteItemById(String id) {
		if (!itemRepository.existsById(id)) throw new ItemNotFoundException("Item not found when trying to delete item with ID: " + id);
		
		itemRepository.deleteById(id);
		return "Item deleted successfully with ID: " + id;
	}

}
