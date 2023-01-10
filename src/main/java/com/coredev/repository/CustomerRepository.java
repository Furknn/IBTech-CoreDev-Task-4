package com.coredev.repository;
import com.coredev.entity.Customer;

public class CustomerRepository extends BaseRepository<Customer>{
	public CustomerRepository() {
		super(Customer.class);
	}
}
