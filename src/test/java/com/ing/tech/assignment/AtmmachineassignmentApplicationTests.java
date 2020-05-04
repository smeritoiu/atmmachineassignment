package com.ing.tech.assignment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ing.tech.assignment.model.Account;
import com.ing.tech.assignment.service.AccountService;

@SpringBootTest
class AtmmachineassignmentApplicationTests {
	
	@Autowired
	private AccountService service;

	@Test
	void testCreateAccount() {
		Account account = new Account();
		account.setAccountNumber("RO32INBG25");
		account.setCustomerName("Silvia");
		account.setPin(1234);
		account.setBalance(250);
		
		service.save(account);
	}
	
	@Test
	void testFindCustomerByPIN() {
		Account findAccount = service.findAccountByPIN(1234);
		System.out.println(findAccount);
	}

}
