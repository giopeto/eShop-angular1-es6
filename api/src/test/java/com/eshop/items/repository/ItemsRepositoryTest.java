package com.eshop.items.repository;

import com.eshop.configuration.ApplicationTestPersistentConfig;
import com.eshop.items.domain.Items;
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

import static com.eshop.items.utils.ItemsTestUtils.generateItems;
import static java.util.UUID.randomUUID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:test.properties")
@ContextConfiguration(classes = ApplicationTestPersistentConfig.class)
public class ItemsRepositoryTest {

	private static final int NUMBER_OF_TEST_RECORDS = 10;

	@Autowired private ItemsRepository itemsRepository;

	private List<Items> items;
	String groupId;

	@Before
	public void setUp() throws Exception {
		groupId = randomUUID().toString();
		items = generateItems(NUMBER_OF_TEST_RECORDS, groupId);
		itemsRepository.save(items);
	}

	@After
	public void tearDown() throws Exception {
		itemsRepository.deleteAll();
	}

	@Test
	public void testFindById() throws Exception {
		Items expectedItems = items.get(0);
		Items actualItems = itemsRepository.findById(expectedItems.getId());

		assertEquals(expectedItems, actualItems);
	}

	@Test
	public void testFindByGroupId() throws Exception {
		assertEquals(items, itemsRepository.findByGroupId(groupId));
	}

	@Test
	public void testDeleteById() throws Exception {
		Items itemsForDelete = items.get(0);
		itemsRepository.deleteById(itemsForDelete.getId());

		Optional<Items> deletedItems = itemsRepository.findAll().stream()
			.filter(item -> item.getId().equals(itemsForDelete.getId()))
			.findAny();

		assertFalse(deletedItems.isPresent());
	}
}