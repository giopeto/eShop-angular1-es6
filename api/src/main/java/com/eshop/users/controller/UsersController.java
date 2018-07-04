package com.eshop.users.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class UsersController {

	@RequestMapping(value = "/403", method = GET)
	public void accessDenied(Principal principal) {
		System.out.println("Principal: " + principal);
	}
}
