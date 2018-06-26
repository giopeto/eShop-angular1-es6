package com.eshop.groups.service;

import com.eshop.groups.domain.Group;
import com.eshop.groups.repository.GroupRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GroupServiceTest {

	private GroupService groupService;

	@Mock private GroupRepository groupRepository;

	private Group group;
	private String groupId = "1-2-3-4";

	@Before
	public void init() {
		groupService = new GroupServiceImpl(groupRepository);
		group = new Group(null, "Group name");
	}

	@Test
	public void testSave() {
		Group savedGroup = new Group(groupId, group.getName());

		when(groupRepository.save(group)).thenReturn(savedGroup);

		Group resultGroup = groupService.save(group);
		assertEquals(groupId, resultGroup.getId());
		verify(groupRepository).save(group);
	}

	@Test
	public void testGet() {
		List allGroupsOrderByName = asList(
			new Group("1-2-3-4", "Group 1"),
			new Group("5-6-7-8", "Group 2")
		);

		when(groupRepository.findAllByOrderByNameAsc()).thenReturn(allGroupsOrderByName);

		assertEquals(groupService.get(), allGroupsOrderByName);
		verify(groupRepository).findAllByOrderByNameAsc();
	}

	@Test
	public void testGetById() {
		when(groupRepository.findById(groupId)).thenReturn(group);

		assertEquals(groupService.getById(groupId), group);
		verify(groupRepository).findById(groupId);
	}

	@Test
	public void testDelete() {
		String result = "1";
		when(groupRepository.deleteById(groupId)).thenReturn(result);

		assertEquals(groupService.delete(groupId), result);
		verify(groupRepository).deleteById(groupId);
	}
}
