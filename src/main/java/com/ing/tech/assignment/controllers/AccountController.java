package com.ing.tech.assignment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ing.tech.assignment.model.Account;
import com.ing.tech.assignment.service.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	AccountService service;
	
	@RequestMapping(value = "/show/{pin}", method = RequestMethod.GET)
	public Account showAccountDetails(@PathVariable("pin") int pin) {
		return service.findAccountByPIN(pin);
	}

}
