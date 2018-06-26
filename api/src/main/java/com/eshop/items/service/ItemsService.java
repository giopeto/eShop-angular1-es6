package com.eshop.items.service;

import com.eshop.items.domain.Items;

import java.util.List;

public interface ItemsService {

	public Items save(Items item);
	public List<Items> get();
	public Items findById(String id);
	public List<Items> findByGroupId(String groupId);
	public String delete(String id);
	public void deleteImageToItem(String id);
}
