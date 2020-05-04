package com.ing.tech.assignment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ing.tech.assignment.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Account> findByPin(int pin);

}
