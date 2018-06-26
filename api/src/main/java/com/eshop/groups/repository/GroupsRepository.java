package com.eshop.groups.repository;

import com.eshop.groups.domain.Groups;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupsRepository extends MongoRepository<Groups, String> {

	Groups findById(String id);
	List<Groups> findAllByOrderByNameAsc();
	String deleteById(String id);
}