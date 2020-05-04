package com.ing.tech.assignment.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ing.tech.assignment.model.Account;
import com.ing.tech.assignment.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService service;

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

	@RequestMapping(value = "/show/{pin}", method = RequestMethod.GET)
	public Account showAccountDetails(@PathVariable("pin") int pin) {
		LOGGER.info("Finding account for PIN: " + pin);
		return service.findAccountByPIN(pin);
	}

	@RequestMapping(value = "/deposit", method = RequestMethod.PUT)
	public Account depositAmountOfMoney(@RequestBody Account account) {
		LOGGER.info("Trying to deposit " + account.getBalance() + " into account using PIN: " + account.getPin());
		return service.depositMoneyToAccount(account);
	}

	@RequestMapping(value = "/withdraw", method = RequestMethod.PUT)
	public Account withdrawAmountOfMoney(@RequestBody Account account) {
		LOGGER.info("Trying to withdraw " + account.getWithdrawValue() + " from account using PIN: " + account.getPin());
		return service.withdrawMoneyFromAccount(account);
	}

}
