package com.eshop.items.service;

import com.eshop.files.service.FilesToDomainMapperService;
import com.eshop.items.domain.Items;
import com.eshop.items.repository.ItemsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static com.eshop.items.utils.ItemsTestUtils.generateItem;
import static com.eshop.items.utils.ItemsTestUtils.generateItems;
import static java.util.UUID.randomUUID;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemsServiceTest {

	private ItemsService itemsService;

	@Mock private ItemsRepository itemsRepository;
	@Mock private FilesToDomainMapperService filesToDomainMapperService;

	private String itemId;
	private String groupId;
	private Items item;

	@Before
	public void setUp() throws Exception {
		itemsService = new ItemsServiceImpl(itemsRepository, filesToDomainMapperService);
		itemId = randomUUID().toString();
		groupId = randomUUID().toString();

		item = generateItem(null, Optional.empty(), groupId);
	}

	@Test
	public void testSave() throws Exception {
		Items savedItems = generateItem(itemId, Optional.of(item.getName()), item.getGroupId());

		when(itemsRepository.save(item)).thenReturn(savedItems);

		Items resultItems = itemsService.save(item);
		assertEquals(itemId, resultItems.getId());
	}

	@Test
	public void testGet() throws Exception {
		List allItems = generateItems(2, groupId);

		when(itemsRepository.findAll()).thenReturn(allItems);

		assertEquals(itemsService.get(), allItems);
	}

	@Test
	public void testFindById() throws Exception {
		when(itemsRepository.findById(itemId)).thenReturn(item);

		assertEquals(itemsService.findById(itemId), item);
	}

	@Test
	public void testFindByGroupId() throws Exception {
		List allItems = generateItems(2, groupId);

		when(itemsRepository.findByGroupId(groupId)).thenReturn(allItems);

		assertEquals(itemsService.findByGroupId(groupId), allItems);
	}

	@Test
	public void testDelete() throws Exception {
		String result = "1";
		when(itemsRepository.deleteById(itemId)).thenReturn(result);

		assertEquals(itemsService.delete(itemId), result);
	}

	@Test
	public void testDeleteImageToItem() throws Exception {
	}

}