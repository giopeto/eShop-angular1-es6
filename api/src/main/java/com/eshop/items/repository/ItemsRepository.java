package com.eshop.items.repository;

import com.eshop.items.domain.Items;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends MongoRepository<Items, String> {

	Items findById(String id);
	String deleteById(String id);
	List findByGroupId(String groupId);
}
