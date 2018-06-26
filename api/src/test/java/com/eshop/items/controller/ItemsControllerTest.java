package com.eshop.items.controller;

import com.eshop.groups.domain.Groups;
import com.eshop.items.domain.Items;
import com.eshop.items.service.ItemsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static com.eshop.common.ApiConstants.API_BASE_URL;
import static com.eshop.groups.utils.GroupsTestUtils.generateGroup;
import static com.eshop.items.utils.ItemsTestUtils.generateItem;
import static java.util.Collections.singletonList;
import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ItemsControllerTest {

	private static final String ITEMS_URL = "/" + API_BASE_URL + "/items";

	private ItemsController itemsController;

	@Mock private ItemsService itemsService;

	private MockMvc mockMvc;
	private ObjectMapper jacksonObjectMapper;
	private String id;
	private Items items;
	private String groupId;
	private Groups groups;

	@Before
	public void setUp() throws Exception {
		itemsController = new ItemsController(itemsService);

		mockMvc = MockMvcBuilders.standaloneSetup(itemsController).build();
		jacksonObjectMapper = new ObjectMapper();
		id = randomUUID().toString();
		groupId = randomUUID().toString();
		items = generateItem(id, Optional.empty(), groupId);
		groups = generateGroup(groupId, Optional.empty());
	}

	@Test
	public void testSave() throws Exception {
		Items newItems = generateItem(null, Optional.empty(), groupId);
		Items savedItems = generateItem(id, Optional.empty(), groupId);

		when(itemsService.save(newItems)).thenReturn(savedItems);

		String request = jacksonObjectMapper.writeValueAsString(newItems);
		String response = jacksonObjectMapper.writeValueAsString(savedItems);

		mockMvc.perform(post(ITEMS_URL)
			.content(request)
			.contentType(APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().string(response));
	}

	@Test
	public void testUpdate() throws Exception {
		items.setName("Updated name");
		String request = jacksonObjectMapper.writeValueAsString(groups);

		mockMvc.perform(put(ITEMS_URL + "/" + id)
			.content(request)
			.contentType(APPLICATION_JSON))
			.andExpect(status().isOk());
	}

	@Test
	public void testGet() throws Exception {
		when(itemsService.get()).thenReturn(singletonList(items));

		String response = jacksonObjectMapper.writeValueAsString(itemsService.get());

		mockMvc.perform(get(ITEMS_URL))
			.andExpect(status().isOk())
			.andExpect(content().string(response));
	}

	@Test
	public void getById() throws Exception {
		when(itemsService.findById(id)).thenReturn(items);

		String response = jacksonObjectMapper.writeValueAsString(itemsService.findById(id));

		mockMvc.perform(get(ITEMS_URL + "/" + id))
			.andExpect(status().isOk())
			.andExpect(content().string(response));
	}

	@Test
	public void testGetByGroupId() throws Exception {
		when(itemsService.findByGroupId(id)).thenReturn(singletonList(items));

		String response = jacksonObjectMapper.writeValueAsString(itemsService.findByGroupId(id));

		mockMvc.perform(get(ITEMS_URL + "/getByGroupId/" + id))
			.andExpect(status().isOk())
			.andExpect(content().string(response));
	}

	@Test
	public void testDelete() throws Exception {
		when(itemsService.delete(id)).thenReturn("1");

		mockMvc.perform(delete(ITEMS_URL + "/" + id)
			.contentType(APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().string("1"));
	}
}