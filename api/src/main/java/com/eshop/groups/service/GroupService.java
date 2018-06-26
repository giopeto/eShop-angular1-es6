package com.eshop.groups.service;

import com.eshop.groups.domain.Group;

import java.util.List;

public interface GroupService {

	public Group save(Group group);

	public List get();

	public Group getById(String id);

	public String delete(String id);
}
