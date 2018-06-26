package com.eshop.groups.service;

import com.eshop.groups.domain.Groups;

import java.util.List;

public interface GroupsService {

	public Groups save(Groups groups);
	public List get();
	public Groups findById(String id);
	public String delete(String id);
}
