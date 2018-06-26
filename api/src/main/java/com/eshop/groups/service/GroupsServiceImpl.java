package com.eshop.groups.service;

import com.eshop.groups.domain.Groups;
import com.eshop.groups.repository.GroupsRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupsServiceImpl implements GroupsService {

	@NonNull private final GroupsRepository groupsRepository;

	@Autowired
	public GroupsServiceImpl(GroupsRepository groupsRepository) {
		this.groupsRepository = groupsRepository;
	}

	public Groups save(Groups groups) {
		return groupsRepository.save(groups);
	}

	public List<Groups> get() {
		return groupsRepository.findAllByOrderByNameAsc();
	}

	public Groups findById(String id) {
		return groupsRepository.findById(id);
	}

	public String delete(String id) {
		return groupsRepository.deleteById(id);
	}

}
