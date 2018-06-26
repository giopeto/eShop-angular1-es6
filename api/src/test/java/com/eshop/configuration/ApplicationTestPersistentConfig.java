package com.eshop.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ApplicationTestConfig.class, MongoConfig.class})
public class ApplicationTestPersistentConfig {

	// Todo run in mongo in docker container
}
