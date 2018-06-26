package com.eshop.groups.controller;

import com.eshop.groups.domain.Group;
import com.eshop.groups.service.GroupService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.eshop.common.ApiConstants.API_BASE_URL;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class GroupControllerTest {

	private static final String GROUP_URL = "/" + API_BASE_URL + "/groups";

	private GroupController groupController;

	@Mock GroupService groupService;

	private MockMvc mockMvc;
	private ObjectMapper jacksonObjectMapper;
	private Group group;
	private String id;

	@Before
	public void setUp() {
		groupController = new GroupController(groupService);

		mockMvc = MockMvcBuilders.standaloneSetup(groupController).build();
		jacksonObjectMapper = new ObjectMapper();
		id = "1-2-3-4";
		group = new Group(id, "Group 1");
	}

	@Test
	public void testGet() throws Exception {
		when(groupService.get()).thenReturn(singletonList(group));

		String response = jacksonObjectMapper.writeValueAsString(groupService.get());

		mockMvc.perform(get(GROUP_URL))
			.andExpect(status().isOk())
			.andExpect(content().string(response));
	}

	@Test
	public void testGetById() throws Exception {
		when(groupService.getById(id)).thenReturn(group);

		String response = jacksonObjectMapper.writeValueAsString(groupService.getById(id));

		mockMvc.perform(get(GROUP_URL + "/" + id))
			.andExpect(status().isOk())
			.andExpect(content().string(response));
	}

	@Test
	public void testSave() throws Exception {
		String newGroupName = "New Group";
		Group newGroup = new Group(null, newGroupName);
		Group savedGroup = new Group("5-6-7-8", newGroupName);

		when(groupService.save(newGroup)).thenReturn(savedGroup);

		String request = jacksonObjectMapper.writeValueAsString(newGroup);
		String response = jacksonObjectMapper.writeValueAsString(savedGroup);

		mockMvc.perform(post(GROUP_URL)
			.content(request)
			.contentType(APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().string(response));
	}

	@Test
	public void testUpdate() throws Exception {
		group.setName("Updated name");
		String request = jacksonObjectMapper.writeValueAsString(group);

		mockMvc.perform(put(GROUP_URL + "/" + id)
			.content(request)
			.contentType(APPLICATION_JSON))
			.andExpect(status().isOk());
	}

	@Test
	public void testDelete() throws Exception {
		when(groupService.delete(id)).thenReturn("1");

		mockMvc.perform(delete(GROUP_URL + "/" + id)
			.contentType(APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().string("1"));
	}
}