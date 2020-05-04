package com.ing.tech.assignment;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ing.tech.assignment.model.Account;
import com.ing.tech.assignment.repository.AccountRepository;

@SpringBootTest
class AtmmachineassignmentApplicationTests {
	
	@Autowired
	private AccountRepository repository;

	@Test
	void testCreateAccount() {
		Account account = new Account();
		account.setAccountNumber("RO32INBG25");
		account.setCustomerName("Silvia");
		account.setPin(1234);
		account.setBalance(250);
		
		repository.save(account);
	}
	
	
	@Test
	void testFindCustomerByPIN() {
		Optional<Account> findByPin = repository.findByPin(1234);
		
		//System.out.println(findAccount);
	}
	

}
