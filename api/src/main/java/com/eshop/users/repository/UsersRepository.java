package com.eshop.users.repository;

import com.eshop.users.domain.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends MongoRepository<Users, String> {

	Users findOneByEmail(String email);
	List<Users> findByIdIn (List<String> ids);
}
