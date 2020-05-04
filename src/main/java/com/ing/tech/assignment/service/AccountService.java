package com.ing.tech.assignment.service;

import com.ing.tech.assignment.model.Account;

public interface AccountService {
	
	public Account findAccountByPIN(int pin);
	public Account depositMoneyToAccount(Account account);
	public Account withdrawMoneyFromAccount(Account account);
	
	//de sters
	public Account save(Account account);

}
