package com.eshop.items.service;

import com.eshop.files.service.FilesToDomainMapperService;
import com.eshop.items.domain.Items;
import com.eshop.items.repository.ItemsRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsServiceImpl implements ItemsService {

	@NonNull private final ItemsRepository itemsRepository;
	@NonNull private final FilesToDomainMapperService filesToDomainMapperService;

	@Autowired
	public ItemsServiceImpl(ItemsRepository itemsRepository, FilesToDomainMapperService filesToDomainMapperService) {
		this.itemsRepository = itemsRepository;
		this.filesToDomainMapperService = filesToDomainMapperService;
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
		filesToDomainMapperService.deleteByDomainId(id);
		return itemsRepository.deleteById(id);
	}

	@Override
	public void deleteImageToItem(String id) {



	}
}
