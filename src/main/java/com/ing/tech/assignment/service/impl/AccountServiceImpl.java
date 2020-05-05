package com.ing.tech.assignment.service.impl;

import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.tech.assignment.constants.ErrorMessagesConstants;
import com.ing.tech.assignment.constants.InfoMessagesConstants;
import com.ing.tech.assignment.exceptions.AccountBalanceException;
import com.ing.tech.assignment.exceptions.IncorrectPINException;
import com.ing.tech.assignment.model.Account;
import com.ing.tech.assignment.repository.AccountRepository;
import com.ing.tech.assignment.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository repository;

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Override
	public Account findAccountByPIN(int pin) {
		return findAccount(pin);
	}

	@Override
	public Account depositMoneyToAccount(Account account) {
		Account accountFound = findAccount(account.getPin());
		accountFound.setBalance(accountFound.getBalance() + account.getBalance());
		Account updatedAccount = repository.save(accountFound);
		LOGGER.info(InfoMessagesConstants.UPDATED_ACCOUNT + updatedAccount.getBalance());
		return updatedAccount;

	}

	@Override
	public Account withdrawMoneyFromAccount(Account account) {
		Account accountFound = findAccount(account.getPin());
		if (accountFound.getBalance() > account.getWithdrawValue()) {
			accountFound.setBalance(accountFound.getBalance() - account.getWithdrawValue());
			Account updatedAccount = repository.save(accountFound);
			LOGGER.info(InfoMessagesConstants.UPDATED_ACCOUNT + updatedAccount.getBalance());
			return updatedAccount;
		} else {
			LOGGER.error(ErrorMessagesConstants.INSUFFICIENT_FUNDS);
			throw new AccountBalanceException(ErrorMessagesConstants.INSUFFICIENT_FUNDS);
		}
	}

	private Account findAccount(int pin) {
		return repository.findByPin(pin).orElseThrow((Supplier<? extends IncorrectPINException>) () -> {
			LOGGER.error(ErrorMessagesConstants.PIN_INCORRECT);
			return new IncorrectPINException(ErrorMessagesConstants.PIN_INCORRECT);
		});
	}

}
