package com.eshop.items.service;

import com.eshop.items.domain.Items;
import com.eshop.items.repository.ItemsRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsServiceImpl implements ItemsService {

	@NonNull private final ItemsRepository itemsRepository;

	@Autowired
	public ItemsServiceImpl(ItemsRepository itemsRepository) {
		this.itemsRepository = itemsRepository;
	}

	@Override
	public Items save(Items item) {
		return itemsRepository.save(item);
	}

	@Override
	public List<Items> get() {
		return itemsRepository.findAll();
	}

	@Override
	public Items findById(String id) {
		return itemsRepository.findById(id);
	}

	@Override
	public List<Items> findByGroupId(String groupId) {
		return itemsRepository.findByGroupId(groupId);
	}

	@Override
	public String delete(String id) {
		return itemsRepository.deleteById(id);
	}

	@Override
	public void deleteImageToItem(String id) {

	}
}
