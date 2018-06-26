package com.eshop.groups.service;

import com.eshop.groups.domain.Group;
import com.eshop.groups.repository.GroupRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

	@NonNull private final GroupRepository groupRepository;

	@Autowired
	public GroupServiceImpl(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	public Group save(Group group) {
		return groupRepository.save(group);
	}

	public List<Group> get() {
		return groupRepository.findAllByOrderByNameAsc();
	}

	public Group getById(String id) {
		return groupRepository.findById(id);
	}

	public String delete(String id) {
		return groupRepository.deleteById(id);
	}

}
