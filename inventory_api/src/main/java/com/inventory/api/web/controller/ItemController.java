package com.inventory.api.web.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.api.data.domain.Item;
import com.inventory.api.data.models.ItemName;
import com.inventory.api.data.models.Price;
import com.inventory.api.data.models.Size;
import com.inventory.api.data.models.Tag;
import com.inventory.api.service.ItemService;
import com.inventory.api.web.dto.ItemDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/items")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Value("${api.url:localhost}")
	private String url;
	
	@Value("${server.port}")
	private String port;
	
	@Value("${api.items.url}")
	private String itemsUrl;
	
	@GetMapping("/all")
	public ResponseEntity<List<Item>> getAllItems() {
		log.info("Received request for all items");
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Location", url + port + itemsUrl + "all");
		
		Item item1 = Item.builder()
						.id("0j84j3809-tju8340u43")
						.name(new ItemName("Freddo Chocolate Bar", "Freddo"))
						.description("A yummy chocolate bar")
						.price(new Price("00.50", Double.valueOf("00.50")))
						.itemCode("0j84j3809-tju8340u43")
						.stock(666)
						.tags(Set.of(Tag.FOOD))
						.size(new Size(0.50d, 0.50d, 0.50d))
						.build();
		
		Item item2 = Item.builder()
				.id("0j84j3810-tjq7340u45")
				.name(new ItemName("Reddo Fake Bar", "ReddoFake"))
				.description("A yummy fake bar")
				.price(new Price("10.20", Double.valueOf("10.20")))
				.itemCode("0j84j3810-tjq7340u45")
				.stock(666)
				.tags(Set.of(Tag.FOOD))
				.size(new Size(0.56d, 0.46d, 0.56d))
				.build();
		
		return new ResponseEntity<List<Item>>(List.of(item1, item2),
											  headers,
											  HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable String id) {
		log.info("Received item id: " + id);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Location", url + port + itemsUrl + "0j84j3809-tju8340u43");
		
		Item item = Item.builder()
						.id("0j84j3809-tju8340u43")
						.name(new ItemName("Freddo Chocolate Bar", "Freddo"))
						.description("A yummy chocolate bar")
						.price(new Price("00.50", Double.valueOf("00.50")))
						.itemCode("0j84j3809-tju8340u43")
						.stock(666)
						.tags(Set.of(Tag.FOOD))
						.size(new Size(0.50d, 0.50d, 0.50d))
						.build();
		
		return new ResponseEntity<Item>(item, headers, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Item> getItemByItemName(@RequestBody @Valid ItemName itemName) {
		log.info("Received item name: " + itemName.getName() + ", and alias: " + itemName.getAlias());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Location", url + port + itemsUrl + "0j84j3809-tju8340u43");
		
		Item item = Item.builder()
						.id("0j84j3809-tju8340u43")
						.name(new ItemName("Freddo Chocolate Bar", "Freddo"))
						.description("A yummy chocolate bar")
						.price(new Price("00.50", Double.valueOf("00.50")))
						.itemCode("0j84j3809-tju8340u43")
						.stock(666)
						.tags(Set.of(Tag.FOOD))
						.size(new Size(0.50d, 0.50d, 0.50d))
						.build();
		
		return new ResponseEntity<Item>(item, headers, HttpStatus.OK);
	}
	
	@GetMapping("/alias/{alias}")
	public ResponseEntity<Item> getItemByItemAlias(@PathVariable String alias) {
		log.info("Received alias: " + alias);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Location", url + port + itemsUrl + "0j84j3809-tju8340u43");
		
		Item item = Item.builder()
						.id("0j84j3809-tju8340u43")
						.name(new ItemName("Freddo Chocolate Bar", "Freddo"))
						.description("A yummy chocolate bar")
						.price(new Price("00.50", Double.valueOf("00.50")))
						.itemCode("0j84j3809-tju8340u43")
						.stock(666)
						.tags(Set.of(Tag.FOOD))
						.size(new Size(0.50d, 0.50d, 0.50d))
						.build();
		
		return new ResponseEntity<Item>(item, headers, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Item> postNewItem(@RequestBody @Valid ItemDTO itemDTO) {
		log.info("Received new item: " + itemDTO.toString());
		
//		Item returnable = Item.builder()
//							  .id("0j84j3809-tju8340u43")
//							  .name(item.getName())
//							  .description(item.getDescription())
//							  .itemCode(item.getItemCode())
//							  .price(item.getPrice())
//							  .size(item.getSize())
//							  .stock(item.getStock())
//							  .tags(item.getTags())
//							  .build();
		Item returnable = itemService.postNewItem(itemDTO);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Location", url + port + itemsUrl + returnable.getId());
		
		return new ResponseEntity<Item>(returnable, headers, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Item> putUpdateItem(@RequestBody @Valid ItemDTO item) {
		log.info("Received update to item: " + item.toString());
		
		Item returnable = Item.builder()
							  .id("0j84j3809-tju8340u43")
							  .name(item.getName())
							  .description(item.getDescription())
							  .itemCode(item.getItemCode())
							  .price(item.getPrice())
							  .size(item.getSize())
							  .stock(item.getStock())
							  .tags(item.getTags())
							  .build();
		
		HttpHeaders headers = new HttpHeaders();
		// mock ID url
		headers.set("Location", url + port + itemsUrl + returnable.getId());
		
		return new ResponseEntity<Item>(returnable, headers, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteItemById(@PathVariable("id") String id) {
		log.info("Deleting item with ID: " + id);
		
		return new ResponseEntity<String>("Item deleted successfully", HttpStatus.OK);
	}
}
