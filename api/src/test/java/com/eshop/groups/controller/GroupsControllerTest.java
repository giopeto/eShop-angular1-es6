package com.eshop.groups.controller;

import com.eshop.groups.domain.Groups;
import com.eshop.groups.service.GroupsService;
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
public class GroupsControllerTest {

	private static final String GROUP_URL = "/" + API_BASE_URL + "/groups";

	private GroupsController groupsController;

	@Mock private GroupsService groupsService;

	private MockMvc mockMvc;
	private ObjectMapper jacksonObjectMapper;
	private Groups groups;
	private String id;

	@Before
	public void setUp() {
		groupsController = new GroupsController(groupsService);

		mockMvc = MockMvcBuilders.standaloneSetup(groupsController).build();
		jacksonObjectMapper = new ObjectMapper();
		id = randomUUID().toString();
		groups = generateGroup(id, Optional.empty());
	}

	@Test
	public void testSave() throws Exception {
		String newGroupName = "New Groups";
		Groups newGroups = generateGroup(null, Optional.of(newGroupName));
		Groups savedGroups = generateGroup(randomUUID().toString(), Optional.of(newGroupName));
		savedGroups.setName(newGroupName);

		when(groupsService.save(newGroups)).thenReturn(savedGroups);

		String request = jacksonObjectMapper.writeValueAsString(newGroups);
		String response = jacksonObjectMapper.writeValueAsString(savedGroups);

		mockMvc.perform(post(GROUP_URL)
			.content(request)
			.contentType(APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().string(response));
	}

	@Test
	public void testUpdate() throws Exception {
		groups.setName("Updated name");
		String request = jacksonObjectMapper.writeValueAsString(groups);

		mockMvc.perform(put(GROUP_URL + "/" + id)
			.content(request)
			.contentType(APPLICATION_JSON))
			.andExpect(status().isOk());
	}

	@Test
	public void testGet() throws Exception {
		when(groupsService.get()).thenReturn(singletonList(groups));

		String response = jacksonObjectMapper.writeValueAsString(groupsService.get());

		mockMvc.perform(get(GROUP_URL))
			.andExpect(status().isOk())
			.andExpect(content().string(response));
	}

	@Test
	public void testGetById() throws Exception {
		when(groupsService.findById(id)).thenReturn(groups);

		String response = jacksonObjectMapper.writeValueAsString(groupsService.findById(id));

		mockMvc.perform(get(GROUP_URL + "/" + id))
			.andExpect(status().isOk())
			.andExpect(content().string(response));
	}

	@Test
	public void testDelete() throws Exception {
		when(groupsService.delete(id)).thenReturn("1");

		mockMvc.perform(delete(GROUP_URL + "/" + id)
			.contentType(APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().string("1"));
	}
}