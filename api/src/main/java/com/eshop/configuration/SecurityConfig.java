package com.eshop.configuration;

import com.eshop.users.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.eshop.common.ApiConstants.API_BASE_URL;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String ROLE_ADMIN = "'ROLE_ADMIN'";
	private static final String REMEMBER_ME_KEY = "remember-me-key";

	@Autowired private UsersService usersService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new StandardPasswordEncoder();
	}

	@Bean
	public TokenBasedRememberMeServices rememberMeServices() {
		return new TokenBasedRememberMeServices(REMEMBER_ME_KEY, usersService);
	}

	@Bean(name = "authenticationManager")
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
			.eraseCredentials(true)
			.userDetailsService(usersService)
			.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers(POST, "/" + API_BASE_URL + "/users").permitAll()
				.antMatchers(GET, "/" + API_BASE_URL + "/users").permitAll()
				.antMatchers(POST, "/" + API_BASE_URL + "/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers(PUT, "/" + API_BASE_URL + "/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers(DELETE, "/" + API_BASE_URL + "/**").access("hasRole('ROLE_ADMIN')")
			.and()
				.csrf().disable()
				.exceptionHandling().accessDeniedPage("/403")
			/*.and()
				.formLogin()
				.loginProcessingUrl("/perform_login")
			.and()
				.logout()
				.deleteCookies("JSESSIONID")*/
			.and()
				.rememberMe()
				.rememberMeServices(rememberMeServices())
				.key(REMEMBER_ME_KEY)
			.and()
				.cors()
				/*.anyRequest().authenticated()
				.and()

				.loginPage("/app/account/account_add_edit.html")
				.permitAll()
				.failureUrl("/signin?error=1")
				.loginProcessingUrl("/authenticate")
				.and()
				.logout()
				.logoutUrl("/logout")
				.permitAll()
				.logoutSuccessUrl("/signin?logout")
				.and()
				.rememberMe()
				.rememberMeServices(rememberMeServices())
				.key("remember-me-key")*/;
	}



	/*

	@Override
protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
    .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
    .antMatchers("/painel**").access("hasRole('ROLE_ALUNO')")
    .antMatchers("/").access("permitAll")
    .antMatchers("/cadastro**").access("permitAll")
    .antMatchers("/error/**").access("permitAll")
    .and().formLogin().usernameParameter("username").passwordParameter("senha")
    .loginPage("/").loginProcessingUrl("/autenticar")
    .failureUrl("/")
    .defaultSuccessUrl("/painel")
    .and().logout().deleteCookies("remove")
    .invalidateHttpSession(false)
    .logoutUrl("/logout").logoutSuccessUrl("/")
    .and().csrf().disable()
    .exceptionHandling().accessDeniedPage("/403");
    http.sessionManagement().maximumSessions(1).expiredUrl("/logout");
}

	* */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();



		configuration.setAllowedOrigins(unmodifiableList(asList("*")));
		configuration.setAllowedMethods(unmodifiableList(asList("HEAD",
			"GET", "POST", "PUT", "DELETE", "PATCH")));
		// setAllowCredentials(true) is important, otherwise:
		// The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
		configuration.setAllowCredentials(true);
		// setAllowedHeaders is important! Without it, OPTIONS preflight request
		// will fail with 403 Invalid CORS request
		configuration.setAllowedHeaders(unmodifiableList(asList("Authorization", "Cache-Control", "Content-Type")));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
