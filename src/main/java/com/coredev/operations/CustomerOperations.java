package com.coredev.operations;

import com.coredev.entity.Address;
import com.coredev.entity.Customer;
import com.coredev.entity.Phone;
import com.coredev.repository.AddressRepository;
import com.coredev.repository.CustomerRepository;
import com.coredev.repository.PhoneRepository;
import com.coredev.types.CDBag;

public class CustomerOperations {
	public CDBag createCustomer(CDBag inbag) {
		CustomerRepository	customerRepository = new CustomerRepository();
		PhoneRepository	phoneRepository = new PhoneRepository();
		AddressRepository addressRepository = new AddressRepository();

		Phone phone = new Phone();
		phone.setAreaCode((String)inbag.get("areaCode"));
		phone.setNumber((String)inbag.get("number"));
		phoneRepository.insert(phone);

		Address address = new Address();
		address.setStreet((String)inbag.get("street"));
		address.setCity((String)inbag.get("city"));
		address.setState((String)inbag.get("state"));
		address.setZipCode((String)inbag.get("zipCode"));
		addressRepository.insert(address);

		Customer customer = new Customer();
		customer.setName((String)inbag.get("name"));
		customer.setPhone(phone);
		customer.setAddress(address);
		customerRepository.insert(customer);

		CDBag outbag = new CDBag();
		outbag.put("id", customer.getId());
		outbag.put("name", customer.getName());
		outbag.put("areaCode", customer.getPhone().getAreaCode());
		outbag.put("number", customer.getPhone().getNumber());
		outbag.put("street", customer.getAddress().getStreet());
		outbag.put("city", customer.getAddress().getCity());
		outbag.put("state", customer.getAddress().getState());
		outbag.put("zipCode", customer.getAddress().getZipCode());
		return outbag;
	}

	public CDBag readCustomer(CDBag inbag) {
		CustomerRepository	customerRepository = new CustomerRepository();

		Customer customer = customerRepository.find((Long)inbag.get("id"));

		// convert the customer to a CDBag
		CDBag outbag = new CDBag();
		outbag.put("id", customer.getId());
		outbag.put("name", customer.getName());
		outbag.put("areaCode", customer.getPhone().getAreaCode());
		outbag.put("number", customer.getPhone().getNumber());
		outbag.put("street", customer.getAddress().getStreet());
		outbag.put("city", customer.getAddress().getCity());
		outbag.put("state", customer.getAddress().getState());
		outbag.put("zipCode", customer.getAddress().getZipCode());
		return outbag;
	}

	public CDBag updateCustomer(CDBag inbag) {
		CustomerRepository	customerRepository = new CustomerRepository();
		PhoneRepository	phoneRepository = new PhoneRepository();
		AddressRepository addressRepository = new AddressRepository();

		Customer customer = customerRepository.find((Long)inbag.get("id"));
		customer.setName((String)inbag.get("name"));
		customer.getPhone().setAreaCode((String)inbag.get("areaCode"));
		customer.getPhone().setNumber((String)inbag.get("number"));
		customer.getAddress().setStreet((String)inbag.get("street"));
		customer.getAddress().setCity((String)inbag.get("city"));
		customer.getAddress().setState((String)inbag.get("state"));
		customer.getAddress().setZipCode((String)inbag.get("zipCode"));
		customerRepository.update(customer);
		phoneRepository.update(customer.getPhone());
		addressRepository.update(customer.getAddress());

		CDBag bag = new CDBag();
		bag.put("id", customer.getId());

		return this.readCustomer(bag);
	}

	public void deleteCustomer(CDBag inbag) {
		CustomerRepository	customerRepository = new CustomerRepository();
		PhoneRepository	phoneRepository = new PhoneRepository();
		AddressRepository addressRepository = new AddressRepository();

		Customer customer = customerRepository.find((Long)inbag.get("id"));
		customerRepository.delete(customer);
		phoneRepository.delete(customer.getPhone());
		addressRepository.delete(customer.getAddress());
	}
}
