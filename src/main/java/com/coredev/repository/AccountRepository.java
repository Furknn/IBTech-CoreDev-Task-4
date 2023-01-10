package com.coredev.repository;
import com.coredev.entity.Account;

import jakarta.persistence.EntityManager;

public class AccountRepository extends BaseRepository<Account> {
	public AccountRepository() {
		super(Account.class);
	}

	public Account getAccount(String accountNumber) {
		EntityManager entityManager=newManager();
		Account account=entityManager.find(clazz, accountNumber);
		entityManager.close();
		return account;
	}
}
