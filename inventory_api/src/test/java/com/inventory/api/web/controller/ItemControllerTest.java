package com.inventory.api.web.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.inventory.api.data.domain.Item;
import com.inventory.api.data.models.ItemName;
import com.inventory.api.data.models.Price;
import com.inventory.api.data.models.Size;
import com.inventory.api.data.models.Tag;
import com.inventory.api.web.dto.ItemDTO;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private JacksonTester<ItemDTO> jsonItemRequest;
	
	@Autowired
	private JacksonTester<Item> jsonItemResult;
	
	@Autowired
	private JacksonTester<List<Item>> jsonItemResultList;
	
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
	void getAllItemsTest() throws Exception {
		// given
		
		// when
		MockHttpServletResponse response = mockMvc.perform(
				get("/api/items/all"))
					.andReturn().getResponse();
		
		// then
		then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		then(response.getContentAsString())
					 .isEqualTo(jsonItemResultList.write(items).getJson());
	}
}
