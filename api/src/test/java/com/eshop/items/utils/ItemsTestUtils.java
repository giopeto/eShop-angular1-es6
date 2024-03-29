package com.eshop.items.utils;

import com.eshop.items.domain.Items;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.UUID.randomUUID;

@UtilityClass
public class ItemsTestUtils {

	private static final String ITEM_NAME = "Item ";
	private static final String SHORT_DESCRIPTION = "Short description";
	private static final String DESCRIPTION = "Description";
	private static final Date NEW_DATE = new Date();

	public static Items generateItem(String id, Optional<String> name, String groupId) {
		return new Items(
			id,
			name.isPresent() ? name.get() : ITEM_NAME + id,
			groupId,
			SHORT_DESCRIPTION,
			DESCRIPTION,
			1D,
			true,
			NEW_DATE,
			emptyList()
		);
	}

	public static List<Items> generateItems(int numberOfTestRecords, String groupId) {
		List<Items> mockedItems = new ArrayList<>();

		for (int i = 0; i < numberOfTestRecords; i++) {
			mockedItems.add(generateItem(randomUUID().toString(), Optional.empty(), groupId));
		}

		return mockedItems;
	}
}
