package com.eshop.groups.repository;

import com.eshop.configuration.ApplicationTestPersistentConfig;
import com.eshop.groups.domain.Groups;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static com.eshop.groups.utils.GroupsTestUtils.generateGroups;
import static java.util.Comparator.comparing;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:test.properties")
@ContextConfiguration(classes = ApplicationTestPersistentConfig.class)
public class GroupsRepositoryTest {

	private static final int NUMBER_OF_TEST_RECORDS = 10;

	@Autowired private GroupsRepository groupsRepository;

	private List<Groups> groups;

	@Before
	public void setUp() {
		groups = generateGroups(NUMBER_OF_TEST_RECORDS);
		groupsRepository.save(groups);
	}

	@After
	public void tearDown() {
		groupsRepository.deleteAll();
	}

	@Test
	public void findById() throws Exception {
		Groups expectedGroups = groups.get(0);
		Groups actualGroups = groupsRepository.findById(expectedGroups.getId());

		assertEquals(expectedGroups, actualGroups);
	}

	@Test
	public void findAllByOrderByNameAsc() throws Exception {
		groups.sort(comparing(Groups::getName));

		assertEquals(groups, groupsRepository.findAllByOrderByNameAsc());
	}

	@Test
	public void deleteById() throws Exception {
		Groups groupsForDelete = groups.get(0);
		groupsRepository.deleteById(groupsForDelete.getId());

		Optional<Groups> deletedGroup = groupsRepository.findAll().stream()
			.filter(group -> group.getId().equals(groupsForDelete.getId()))
			.findAny();

		assertFalse(deletedGroup.isPresent());
	}
}