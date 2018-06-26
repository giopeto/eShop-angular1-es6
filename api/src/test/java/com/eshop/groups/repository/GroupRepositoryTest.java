package com.eshop.groups.repository;

import com.eshop.configuration.ApplicationTestPersistentConfig;
import com.eshop.groups.domain.Group;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Comparator.comparing;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:test.properties")
@ContextConfiguration(classes = ApplicationTestPersistentConfig.class)
public class GroupRepositoryTest {

	private static final int NUMBER_OF_TEST_RECORDS = 50;

	@Autowired private GroupRepository groupRepository;

	private List<Group> groups;

	@Before
	public void setUp() {
		groups = generateGroups(NUMBER_OF_TEST_RECORDS);
		groupRepository.save(groups);
	}

	@After
	public void tearDown() {
		groupRepository.deleteAll();
	}

	@Test
	public void findById() throws Exception {
		Group expectedGroup = groups.get(0);
		Group actualGroup = groupRepository.findById(expectedGroup.getId());

		assertEquals(expectedGroup, actualGroup);
	}

	@Test
	public void findAllByOrderByNameAsc() throws Exception {
		groups.sort(comparing(Group::getName));

		assertEquals(groups, groupRepository.findAllByOrderByNameAsc());
	}

	@Test
	public void deleteById() throws Exception {
		Group groupForDelete = groups.get(0);
		groupRepository.deleteById(groupForDelete.getId());

		Optional<Group> deletedGroup = groupRepository.findAll().stream()
			.filter(group -> group.getId().equals(groupForDelete.getId()))
			.findAny();

		assertFalse(deletedGroup.isPresent());
	}

	private List<Group> generateGroups(int numberOfTestRecords) {
		List<Group> groups = new ArrayList<>();
		for (int i = 0; i < numberOfTestRecords; i++) {
			groups.add(new Group(UUID.randomUUID().toString(), UUID.randomUUID().toString()));
		}

		return groups;
	}
}