package com.eshop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableMongoRepositories(basePackages = "com.eshop.*.repository")
@ComponentScan(basePackages = "com")
public class ApplicationConfig {

	private static final String DEFAULT_ENCODING = "utf-8";
	private static final long MAX_UPLOAD_SIZE = 20971520; //20mb

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding(DEFAULT_ENCODING);
		resolver.setMaxUploadSize(MAX_UPLOAD_SIZE);
		return resolver;
	}
}

