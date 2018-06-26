package com.eshop.groups.service;

import com.eshop.groups.domain.Groups;
import com.eshop.groups.repository.GroupsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static com.eshop.groups.utils.GroupsTestUtils.generateGroup;
import static com.eshop.groups.utils.GroupsTestUtils.generateGroups;
import static java.util.UUID.randomUUID;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GroupsServiceTest {

	private GroupsService groupsService;

	@Mock private GroupsRepository groupsRepository;

	private Groups groups;
	private String groupId;

	@Before
	public void setUp() {
		groupsService = new GroupsServiceImpl(groupsRepository);
		groups = generateGroup(null, Optional.empty());
		groupId = randomUUID().toString();
	}

	@Test
	public void testSave() {
		Groups savedGroups = generateGroup(groupId, Optional.of(groups.getName()));

		when(groupsRepository.save(groups)).thenReturn(savedGroups);

		Groups resultGroups = groupsService.save(groups);
		assertEquals(groupId, resultGroups.getId());
	}

	@Test
	public void testGet() {
		List allGroupsOrderByName = generateGroups(2);

		when(groupsRepository.findAllByOrderByNameAsc()).thenReturn(allGroupsOrderByName);

		assertEquals(groupsService.get(), allGroupsOrderByName);
	}

	@Test
	public void testGetById() {
		when(groupsRepository.findById(groupId)).thenReturn(groups);

		assertEquals(groupsService.findById(groupId), groups);
	}

	@Test
	public void testDelete() {
		String result = "1";
		when(groupsRepository.deleteById(groupId)).thenReturn(result);

		assertEquals(groupsService.delete(groupId), result);
	}
}
