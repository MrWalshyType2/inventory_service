package com.inventory.api.web.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.operation.preprocess.ContentModifyingOperationPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationPreprocessor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventory.api.data.domain.Item;
import com.inventory.api.data.models.ItemName;
import com.inventory.api.data.models.Price;
import com.inventory.api.data.models.Size;
import com.inventory.api.data.models.Tag;
import com.inventory.api.service.ItemService;
import com.inventory.api.web.dto.ItemDTO;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//Necessary or restdocs won't work
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 80)
@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class })
@AutoConfigureJsonTesters
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

//	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ItemService itemService;
	
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
	void setup(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
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
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
									  .apply(documentationConfiguration(restDocumentation))
									  .alwaysDo(document("{method-name}",
											    preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
									  .build();
	}
	
	@Test
	void getAllItemsTest() throws Exception {
		// given
		given(itemService.getAllItems()).willReturn(items);
		
		// when
		MockHttpServletResponse response = mockMvc.perform(
				RestDocumentationRequestBuilders
				.get("/api/items/all/"))
					.andDo(document("ItemController/getAllItems()"))
					.andReturn().getResponse();
		
		// then
		then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		then(response.getContentAsString())
					 .isEqualTo(jsonItemResultList.write(items)
							 					  .getJson());
	}
	
	@Test
	void getItemByIdTest() throws Exception {
		String id = item1.getId();
		
		// given
		given(itemService.getItemById(Mockito.any())).willReturn(item1);
		
		// when
		MockHttpServletResponse response = mockMvc.perform(
				RestDocumentationRequestBuilders
				.get("/api/items/{id}", id))
					.andDo(document("ItemController/getItemById()",
							pathParameters(
									parameterWithName("id")
									.description("String ID of the item to retrieve")
							),
							responseFields(
									fieldWithPath("id").description("ID of the item").type(String.class),
									
									fieldWithPath("name").description("Name object of the item").type(ItemName.class),
									fieldWithPath("name.name").description("Full name of the item").type(String.class),
									fieldWithPath("name.alias").description("Alias of the item").type(Double.class),
									
									fieldWithPath("description").description("Description of the item").type(String.class),
									
									fieldWithPath("price").description("Price object of the item").type(Price.class),
									fieldWithPath("price.strPrice").description("Price of the item to 2 d.p as a String").type(String.class),
									fieldWithPath("price.price").description("Price of the item as a double").type(Double.class),
									
									fieldWithPath("stock").description("Stock of the item").type(Integer.class),
									
									fieldWithPath("size").description("Size object of the item").type(Size.class),
									fieldWithPath("size.width").description("Width of the item in CM").type(Double.class),
									fieldWithPath("size.height").description("Height of the item in CM").type(Double.class),
									fieldWithPath("size.length").description("Length of the item in CM").type(Double.class),
									
									fieldWithPath("tags").description("Tags for an item").type(Set.class).type(Tag.class),
									
									fieldWithPath("itemCode").description("Item code for the item").type(String.class)
							)))
					.andReturn().getResponse();
		
		then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		then(response.getContentAsString())
					 .isEqualTo(jsonItemResult.write(item1)
							 				  .getJson());
	}
	
	@Test
	void postNewItemTest() throws Exception {
		ItemDTO postableItem = new ItemDTO(null,
										   item1.getName(),
										   item1.getDescription(),
										   item1.getPrice(),
										   item1.getStock(),
										   item1.getSize(),
										   item1.getTags(),
										   "0j84j3809-tju8340u43");
		
		// given
		given(itemService.postNewItem(Mockito.any())).willReturn(item1);
		
		// when
		MockHttpServletResponse response = mockMvc.perform(
				RestDocumentationRequestBuilders
				.post("/api/items")
					.contentType(MediaType.APPLICATION_JSON)
					.content(jsonItemRequest.write(postableItem)
											.getJson()))
					.andDo(document("ItemController/postNewItem()",
							requestFields(
									fieldWithPath("id").ignored(),
									
									fieldWithPath("name").description("Name object of the item").type(ItemName.class),
									fieldWithPath("name.name").description("Full name of the item").type(String.class),
									fieldWithPath("name.alias").description("Alias of the item").type(Double.class),
									
									fieldWithPath("description").description("Description of the item").type(String.class),
									
									fieldWithPath("price").description("Price object of the item").type(Price.class),
									fieldWithPath("price.strPrice").description("Price of the item to 2 d.p as a String").type(String.class),
									fieldWithPath("price.price").description("Price of the item as a double").type(Double.class),
									
									fieldWithPath("stock").description("Stock of the item").type(Integer.class),
									
									fieldWithPath("size").description("Size object of the item").type(Size.class),
									fieldWithPath("size.width").description("Width of the item in CM").type(Double.class),
									fieldWithPath("size.height").description("Height of the item in CM").type(Double.class),
									fieldWithPath("size.length").description("Length of the item in CM").type(Double.class),
									
									fieldWithPath("tags").description("Tags for an item").type(Set.class).type(Tag.class),
									
									fieldWithPath("itemCode").description("Item code for the item").type(String.class)
							),
							responseFields(
									fieldWithPath("id").description("ID of the item").type(String.class),
									
									fieldWithPath("name").description("Name object of the item").type(ItemName.class),
									fieldWithPath("name.name").description("Full name of the item").type(String.class),
									fieldWithPath("name.alias").description("Alias of the item").type(Double.class),
									
									fieldWithPath("description").description("Description of the item").type(String.class),
									
									fieldWithPath("price").description("Price object of the item").type(Price.class),
									fieldWithPath("price.strPrice").description("Price of the item to 2 d.p as a String").type(String.class),
									fieldWithPath("price.price").description("Price of the item as a double").type(Double.class),
									
									fieldWithPath("stock").description("Stock of the item").type(Integer.class),
									
									fieldWithPath("size").description("Size object of the item").type(Size.class),
									fieldWithPath("size.width").description("Width of the item in CM").type(Double.class),
									fieldWithPath("size.height").description("Height of the item in CM").type(Double.class),
									fieldWithPath("size.length").description("Length of the item in CM").type(Double.class),
									
									fieldWithPath("tags").description("Tags for an item").type(Set.class).type(Tag.class),
									
									fieldWithPath("itemCode").description("Item code for the item").type(String.class)
							)))
					.andReturn()
						.getResponse();
		
		// then
		then(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
		then(response.getContentAsString())
					 .isEqualTo(jsonItemResult.write(item1)
							 				  .getJson());
	}
	
	@Test
	void putUpdateItemTest() throws Exception {
		ItemDTO postableItem = new ItemDTO(null,
										   item1.getName(),
										   item1.getDescription(),
										   item1.getPrice(),
										   item1.getStock(),
										   item1.getSize(),
										   item1.getTags(),
										   "0j84j3809-tju8340u43");
		
		// given
		given(itemService.putUpdateItem(Mockito.any(), Mockito.any())).willReturn(item1);
		
		// when
		MockHttpServletResponse response = mockMvc.perform(
				RestDocumentationRequestBuilders
				.put("/api/items/{id}", item1.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(jsonItemRequest.write(postableItem)
											.getJson()))
					.andDo(document("ItemController/putUpdateItem()",
							pathParameters(
									parameterWithName("id")
									.description("ID of the item to be updated")
							),
							requestFields(
									fieldWithPath("id").ignored(),
									
									fieldWithPath("name").description("Name object of the item").type(ItemName.class),
									fieldWithPath("name.name").description("Full name of the item").type(String.class),
									fieldWithPath("name.alias").description("Alias of the item").type(Double.class),
									
									fieldWithPath("description").description("Description of the item").type(String.class),
									
									fieldWithPath("price").description("Price object of the item").type(Price.class),
									fieldWithPath("price.strPrice").description("Price of the item to 2 d.p as a String").type(String.class),
									fieldWithPath("price.price").description("Price of the item as a double").type(Double.class),
									
									fieldWithPath("stock").description("Stock of the item").type(Integer.class),
									
									fieldWithPath("size").description("Size object of the item").type(Size.class),
									fieldWithPath("size.width").description("Width of the item in CM").type(Double.class),
									fieldWithPath("size.height").description("Height of the item in CM").type(Double.class),
									fieldWithPath("size.length").description("Length of the item in CM").type(Double.class),
									
									fieldWithPath("tags").description("Tags for an item").type(Set.class).type(Tag.class),
									
									fieldWithPath("itemCode").description("Item code for the item").type(String.class)
							),
							responseFields(
									fieldWithPath("id").description("ID of the item").type(String.class),
									
									fieldWithPath("name").description("Name object of the item").type(ItemName.class),
									fieldWithPath("name.name").description("Full name of the item").type(String.class),
									fieldWithPath("name.alias").description("Alias of the item").type(Double.class),
									
									fieldWithPath("description").description("Description of the item").type(String.class),
									
									fieldWithPath("price").description("Price object of the item").type(Price.class),
									fieldWithPath("price.strPrice").description("Price of the item to 2 d.p as a String").type(String.class),
									fieldWithPath("price.price").description("Price of the item as a double").type(Double.class),
									
									fieldWithPath("stock").description("Stock of the item").type(Integer.class),
									
									fieldWithPath("size").description("Size object of the item").type(Size.class),
									fieldWithPath("size.width").description("Width of the item in CM").type(Double.class),
									fieldWithPath("size.height").description("Height of the item in CM").type(Double.class),
									fieldWithPath("size.length").description("Length of the item in CM").type(Double.class),
									
									fieldWithPath("tags").description("Tags for an item").type(Set.class).type(Tag.class),
									
									fieldWithPath("itemCode").description("Item code for the item").type(String.class)
							)
					))
					.andReturn()
						.getResponse();
		
		// then
		then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		then(response.getContentAsString())
					 .isEqualTo(jsonItemResult.write(item1)
							 				  .getJson());
	}
	
	@Test
	void deleteItemByIdTest() throws Exception {
		// given
		String id = item1.getId();
		
		// when
		MockHttpServletResponse response = mockMvc.perform(
				delete("/api/items/" + id))
					.andReturn()
						.getResponse();
		
		// then
		then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}
}
