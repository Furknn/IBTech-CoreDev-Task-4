package com.coredev.repository;
import com.coredev.entity.Address;

public class AddressRepository extends BaseRepository<Address> {
	public AddressRepository() {
		super(Address.class);
	}
}
