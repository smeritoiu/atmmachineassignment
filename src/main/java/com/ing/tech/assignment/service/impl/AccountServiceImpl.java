package com.ing.tech.assignment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.tech.assignment.exceptions.AccountBalanceException;
import com.ing.tech.assignment.exceptions.IncorrectPINException;
import com.ing.tech.assignment.model.Account;
import com.ing.tech.assignment.repository.AccountRepository;
import com.ing.tech.assignment.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository repository;

	@Override
	public Account findAccountByPIN(int pin) {
		// to do return only one account?
		return repository.findByPin(pin).orElseThrow(() -> new IncorrectPINException("Codul PIN introdus este gresit."));
	}

	@Override
	public Account depositMoneyToAccount(Account account) {

		Account accountFound = repository.findByPin(account.getPin())
				.orElseThrow(() -> new IncorrectPINException("Codul PIN introdus este gresit."));
		accountFound.setBalance(accountFound.getBalance() + account.getBalance());
		return repository.save(accountFound);

	}

	@Override
	public Account withdrawMoneyFromAccount(Account account) {
		Account accountFound = repository.findByPin(account.getPin())
				.orElseThrow(() -> new IncorrectPINException("Codul PIN introdus este gresit."));
		if (accountFound.getBalance() > account.getWithdrawValue()) {
			accountFound.setBalance(accountFound.getBalance() - account.getWithdrawValue());
			return repository.save(accountFound);
		} else {
			throw new AccountBalanceException("Fonduri insuficiente.");
		}
	}

}
