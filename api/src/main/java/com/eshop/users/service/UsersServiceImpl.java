package com.eshop.users.service;

import com.eshop.users.domain.Users;
import com.eshop.users.repository.UsersRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Collections.singleton;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UsersServiceImpl implements UsersService, UserDetailsService {

	@NonNull private final UsersRepository usersRepository;
	@NonNull private final PasswordEncoder passwordEncoder;

	@Autowired
	public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
		this.usersRepository = usersRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	@Override
	public Users save(Users users) {
		if(usersRepository.findOneByEmail(users.getEmail()) != null){
			throw new UsernameNotFoundException("There is an user with that email address: " + users.getEmail());
		}

		users.setPassword(passwordEncoder.encode(users.getPassword()));
		usersRepository.save(users);

		if (users.getId() != null) {
			signIn(users);
		}

		return users;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		Users users = usersRepository.findOneByEmail(username);
		if (users == null) {
			throw new UsernameNotFoundException("User not found: " + users.getEmail());
		}
		return createUser(users);
	}

	@Override
	public void signIn(Users users) {

	}

	private User createUser(Users users) {
		return new User(users.getEmail(), users.getPassword(), singleton(createAuthority(users)));
	}

	private GrantedAuthority createAuthority(Users users) {
		return new SimpleGrantedAuthority(users.getRole());
	}
}
