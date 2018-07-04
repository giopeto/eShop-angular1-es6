package com.eshop.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static com.eshop.common.ApiConstants.API_BASE_URL;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	private static final String CORS_MAPPING = "/" + API_BASE_URL + "/**";
	private static final String CORS_ORIGIN = "http://localhost:8080";

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping(CORS_MAPPING)
			.allowedOrigins(CORS_ORIGIN);
	}
}