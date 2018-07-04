package com.eshop.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

@Configuration
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private static final String SERVLET_MAPPING = "/";
	private static final String ENCODING = "UTF-8";
	private static final String SPRING_SECURITY_FILTER_CHAIN = "springSecurityFilterChain";
	private static final String DEFAULT_HTML_ESCAPE_KEY = "defaultHtmlEscape";
	private static final String DEFAULT_HTML_ESCAPE_KEY_VALUE = "true";
	private static final String SPRING_PROFILES_ACTIVE = "spring.profiles.active";
	private static final String SPRING_PROFILES_ACTIVE_DEFAULT = "default";

	@Override
	protected String[] getServletMappings() {
		return new String[]{SERVLET_MAPPING};
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {ApplicationConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding(ENCODING);
		characterEncodingFilter.setForceEncoding(true);

		DelegatingFilterProxy securityFilterChain = new DelegatingFilterProxy(SPRING_SECURITY_FILTER_CHAIN);

		return new Filter[]{characterEncodingFilter, securityFilterChain};
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration.setInitParameter(DEFAULT_HTML_ESCAPE_KEY, DEFAULT_HTML_ESCAPE_KEY_VALUE);
		registration.setInitParameter(SPRING_PROFILES_ACTIVE, SPRING_PROFILES_ACTIVE_DEFAULT);
	}
}