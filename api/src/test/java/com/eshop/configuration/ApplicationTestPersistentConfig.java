package com.eshop.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@Import({ApplicationTestConfig.class, MongoConfig.class})
@EnableMongoRepositories(basePackages = "com.eshop.*.repository")
public class ApplicationTestPersistentConfig {

	// Todo run in mongo in docker container
}
