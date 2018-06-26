package com.eshop.items.controller;

import com.eshop.items.domain.Items;
import com.eshop.items.service.ItemsService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.eshop.common.ApiConstants.API_BASE_URL;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping(API_BASE_URL + "/items")
@CrossOrigin
public class ItemsController {

	@NonNull private final ItemsService itemsService;

	@Autowired
	public ItemsController(ItemsService itemsService) {
		this.itemsService = itemsService;
	}

	@RequestMapping(method = POST)
	public Items save(@RequestBody Items items) {
		return itemsService.save(items);
	}

	@RequestMapping(value = "{id}", method = PUT)
	public void update(@PathVariable String id, @RequestBody Items items) {
		itemsService.save(items);
	}

	@RequestMapping(method = GET)
	public List<Items> get() {
		return itemsService.get();
	}

	@RequestMapping(value = "{id}", method = GET)
	public Items getById(@PathVariable String id) {
		return itemsService.findById(id);
	}

	@RequestMapping(value = "/getByGroupId/{groupId}", method = GET)
	public List<Items> getByGroupId(@PathVariable String groupId) {
		return itemsService.findByGroupId(groupId);
	}

	@RequestMapping(value = "{id}", method = DELETE)
	public String delete(@PathVariable String id) {
		return itemsService.delete(id);
	}
}
