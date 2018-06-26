package com.eshop.groups.repository;

import com.eshop.groups.domain.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends MongoRepository<Group, String> {

	Group findById(String id);
	List<Group> findAllByOrderByNameAsc();
	String deleteById(String id);
}