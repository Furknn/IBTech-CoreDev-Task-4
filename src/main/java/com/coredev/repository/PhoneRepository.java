package com.coredev.repository;
import com.coredev.entity.Phone;

public class PhoneRepository extends BaseRepository<Phone> {
	public PhoneRepository() {
		super(Phone.class);
	}
}
