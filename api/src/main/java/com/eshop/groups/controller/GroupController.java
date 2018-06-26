package com.eshop.groups.controller;

import com.eshop.groups.domain.Group;
import com.eshop.groups.service.GroupService;
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
@RequestMapping(API_BASE_URL + "/groups")
@CrossOrigin
public class GroupController {

	@NonNull private final GroupService groupsService;

	@Autowired
	public GroupController(GroupService groupsService) {
		this.groupsService = groupsService;
	}

	@RequestMapping(method = GET)
	public List<Group> get() {
		return groupsService.get();
	}

	@RequestMapping(value = "{id}", method = GET)
	public Group getById(@PathVariable String id) {
		return groupsService.getById(id);
	}

	@RequestMapping(method = POST)
	public Group save(@RequestBody Group group) {
		return groupsService.save(group);
	}

	@RequestMapping(value = "{id}", method = PUT)
	public void update(@PathVariable String id, @RequestBody Group group) {
			groupsService.save(group);
	}

	@RequestMapping(value = "{id}", method = DELETE)
	public String delete(@PathVariable String id) {
		return groupsService.delete(id);
	}

}
