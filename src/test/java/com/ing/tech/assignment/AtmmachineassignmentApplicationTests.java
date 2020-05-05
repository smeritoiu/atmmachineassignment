package com.ing.tech.assignment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ing.tech.assignment.exceptions.AccountBalanceException;
import com.ing.tech.assignment.exceptions.IncorrectPINException;
import com.ing.tech.assignment.model.Account;
import com.ing.tech.assignment.repository.AccountRepository;
import com.ing.tech.assignment.service.AccountService;

@SpringBootTest
class AtmmachineassignmentApplicationTests {

	@Autowired
	private AccountRepository repository;

	@Autowired
	private AccountService service;

	@Test
	void test1CreateAccount() {
		Account account = new Account();
		account.setAccountNumber("RO32INBG25");
		account.setCustomerName("Silvia");
		account.setPin(1234);
		account.setBalance(250);
		Account save = repository.save(account);
		assertNotNull(save);
	}

	@Test
	void test2FindCustomerByPIN() {
		Account accountByPIN = service.findAccountByPIN(1234);
		assertEquals("Silvia", accountByPIN.getCustomerName());
	}

	@Test
	void test3DepositMoneyToAccount() {
		Account existingAccount = new Account();
		existingAccount.setPin(1234);
		existingAccount.setBalance(300);
		Account moneyToAccount = service.depositMoneyToAccount(existingAccount);
		assertThat(moneyToAccount.getBalance()).isEqualTo(550);
	}

	@Test
	void test4WithdrawMoneyFromAccount() {
		Account existingAccount = new Account();
		existingAccount.setPin(1234);
		existingAccount.setWithdrawValue(300);
		Account moneyFromAccount = service.withdrawMoneyFromAccount(existingAccount);
		assertNotNull(moneyFromAccount);
		assertEquals(250, moneyFromAccount.getBalance());
	}

	@Test
	void test5WithdrawMoreMoneyFromAccount() {
		Account existingAccount = new Account();
		existingAccount.setPin(1234);
		existingAccount.setWithdrawValue(300);
		assertThrows(AccountBalanceException.class, () -> {
			service.withdrawMoneyFromAccount(existingAccount);
		});
	}
	
	@Test
	void test6WrongPinToAccount() {
		assertThrows(IncorrectPINException.class, () -> {
			service.findAccountByPIN(7777);
		});
	}

}
