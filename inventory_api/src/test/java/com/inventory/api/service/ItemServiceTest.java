package com.inventory.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inventory.api.data.domain.Item;
import com.inventory.api.data.domain.document.ItemDocument;
import com.inventory.api.data.models.ItemName;
import com.inventory.api.data.models.Price;
import com.inventory.api.data.models.Size;
import com.inventory.api.data.models.Tag;
import com.inventory.api.data.repository.ItemRepository;
import com.inventory.api.web.dto.ItemDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ItemServiceTest {

	@MockBean
	private ItemRepository itemRepository;
	
	@Autowired
	private ItemService itemService;
	
	private Item item1;
	private Item item2;
	private List<Item> items;
	
	@BeforeEach
	void setup() {
		item1 = Item.builder()
			.id("0j84j3809-tju8340u43")
			.name(new ItemName("Freddo Chocolate Bar", "Freddo"))
			.description("A yummy chocolate bar")
			.price(new Price("00.50", Double.valueOf("00.50")))
			.itemCode("0j84j3809-tju8340u43")
			.stock(666)
			.tags(Set.of(Tag.FOOD))
			.size(new Size(0.50d, 0.50d, 0.50d))
			.build();

		item2 = Item.builder()
			.id("0j84j3810-tjq7340u45")
			.name(new ItemName("Reddo Fake Bar", "ReddoFake"))
			.description("A yummy fake bar")
			.price(new Price("10.20", Double.valueOf("10.20")))
			.itemCode("0j84j3810-tjq7340u45")
			.stock(666)
			.tags(Set.of(Tag.FOOD))
			.size(new Size(0.56d, 0.46d, 0.56d))
			.build();
		
		items = List.of(item1, item2);
	}
	
	@Test
	void getItemByIdTest() {
		Mockito.when(itemRepository.findById(Mockito.any()))
			   .thenReturn(
					   Optional.of(new ItemDocument(item1))
			   );
		
		Item retrievedItem = itemService.getItemById("0j84j3809-tju8340u43");
		
		assertEquals(item1, retrievedItem);
	}
	
	@Test
	void getAllItemsTest() {
		Mockito.when(itemRepository.count()).thenReturn(1L);
		
		Mockito.when(itemRepository.findAll())
			   .thenReturn(items.stream()
					   			.map(ItemDocument::new)
					   			.collect(Collectors.toList())
					   	  );
		
		List<Item> retrievedItems = itemService.getAllItems();
		
		assertEquals(items, retrievedItems);
	}
	
	@Test
	void postNewItemTest() {
		Mockito.when(itemRepository.save(Mockito.any()))
			   .thenReturn(new ItemDocument(item1));
		
		Item retrievedItem = itemService.postNewItem(new ItemDTO(item1));
		
		assertEquals(item1, retrievedItem);
	}
	
	@Test
	void putUpdateTest() {
		Mockito.when(itemRepository.findById(Mockito.any()))
		       .thenReturn(Optional.of(new ItemDocument(item1)));
		
		Mockito.when(itemRepository.save(Mockito.any()))
		       .thenReturn(new ItemDocument(item1));
		
		Item retrievedItem = itemService.putUpdateItem(new ItemDTO(item1), item1.getId());
		
		assertEquals(item1, retrievedItem);
	}
	
	@Test
	void deleteItemByIdTest() {
		Mockito.when(itemRepository.existsById(Mockito.anyString()))
			   .thenReturn(true);
		
		String response = itemService.deleteItemById(item1.getId());
		
		assertEquals("Item deleted successfully with ID: " + item1.getId(), response);
	}
}
