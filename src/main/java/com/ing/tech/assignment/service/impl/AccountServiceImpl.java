package com.ing.tech.assignment.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.tech.assignment.exceptions.PinNotFoundException;
import com.ing.tech.assignment.model.Account;
import com.ing.tech.assignment.repository.AccountRepository;
import com.ing.tech.assignment.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository repository;

	@Override
	public Account findAccountByPIN(int pin) {
		//to do return only one account?
		return repository.findByPin(pin)
				.orElseThrow(() -> new PinNotFoundException("Codul PIN introdus este gresit."));
	}

	@Override
	public Account depositMoneyToAccount(Account account) {
		Optional<Account> findByPin = repository.findByPin(account.getPin());
		if (findByPin.isPresent()) {
			Account foundedAcount = findByPin.get();
			foundedAcount.setBalance(foundedAcount.getBalance() + account.getBalance());
			return repository.save(foundedAcount);
		}
		// handle exception
		return null;
	}

	@Override
	public Account withdrawMoneyFromAccount(Account account) {
		Optional<Account> findByPin = repository.findByPin(account.getPin());
		if (findByPin.isPresent()) {
			Account foundedAcount = findByPin.get();
			if (foundedAcount.getBalance() > account.getWithdrawValue()) {
				foundedAcount.setBalance(foundedAcount.getBalance() - account.getWithdrawValue());
				return repository.save(foundedAcount);
			}
			// to do handle if balance is lower than withdraw
		}
		// to do handle exception
		return null;
	}

	// de sters - doar pt testat
	@Override
	public Account save(Account account) {
		return repository.save(account);
	}

}
