package com.eshop.users.service;

import com.eshop.users.domain.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UsersService extends UserDetailsService {

	public Users save(Users users);
	public Users findOneByEmail(String email);
	public void signIn(Users users);

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;


}