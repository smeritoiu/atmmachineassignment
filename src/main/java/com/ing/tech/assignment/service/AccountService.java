package com.ing.tech.assignment.service;

import com.ing.tech.assignment.model.Account;

public interface AccountService {

	/**
	 * @param pin - pin code that belong to the searched account
	 * @return details of the account if the pin code is correct,
	 *         IncorrectPINException otherwise
	 */
	public Account findAccountByPIN(int pin);

	/**
	 * @param account that has to be updated with the deposed amount
	 * @return details of the account with the updated balance if the pin code is
	 *         correct, IncorrectPINException otherwise
	 */
	public Account depositMoneyToAccount(Account account);

	/**
	 * @param account from witch the customer wants to withdraw a certain amount of
	 *                money
	 * @return details of the account with the updated balance if there is enough
	 *         money in the account, AccountBalanceException otherwise
	 */
	public Account withdrawMoneyFromAccount(Account account);

}
