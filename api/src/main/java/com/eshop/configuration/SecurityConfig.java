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

import static com.eshop.common.ApiConstants.API_BASE_URL;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String ROLE_ADMIN = "'ROLE_ADMIN'";
	@Autowired private UsersService usersService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new StandardPasswordEncoder();
	}

	@Bean
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
				.antMatchers(POST, "/" + API_BASE_URL + "/**").access("hasRole(" + ROLE_ADMIN + ")")
				.antMatchers(PUT, "/" + API_BASE_URL + "/**").access("hasRole(" + ROLE_ADMIN + ")")
				.antMatchers(DELETE, "/" + API_BASE_URL + "/**").access("hasRole(" + ROLE_ADMIN + ")")
			.and()
				.csrf().disable()
				.exceptionHandling().accessDeniedPage("/403");
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
}
