package com.eshop.users.service;

import com.eshop.users.domain.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UsersService extends UserDetailsService {

	public Users save(Users users);
	public void signIn(Users users);

	@Override
	UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}