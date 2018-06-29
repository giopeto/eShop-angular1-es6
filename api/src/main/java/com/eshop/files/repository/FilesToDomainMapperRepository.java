package com.eshop.files.repository;

import com.eshop.files.domain.FilesToDomainMapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesToDomainMapperRepository extends MongoRepository<FilesToDomainMapper, String> {

	FilesToDomainMapper findByDomainId(String domainId);
}
