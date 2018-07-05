package com.eshop.users.controller;

import com.eshop.users.domain.Users;
import com.eshop.users.service.UsersService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static com.eshop.common.ApiConstants.API_BASE_URL;
import static org.springframework.util.Assert.notNull;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(API_BASE_URL + "/users")
public class UsersController {

	@NonNull private final UsersService usersService;

	@Autowired
	public UsersController(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping(method = POST)
	public Users save(@RequestBody Users users) {
		return usersService.save(users);
	}

	@RequestMapping(method = GET)
	public Users getCurrentAccount(Principal principal) {
		System.out.println(principal);
		System.out.println(principal);
		notNull(principal);
		return usersService.findOneByEmail(principal.getName());
	}
}
