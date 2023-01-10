package com.coredev.operations;

import com.coredev.entity.Address;
import com.coredev.repository.AddressRepository;
import com.coredev.types.CDBag;

public class AddressOperations {
    public CDBag createAddress(CDBag inbag) {
        CDBag outBag = new CDBag();
        Address address = new Address();
        address.setStreet((String)inbag.get("street"));
        address.setCity((String)inbag.get("city"));
        address.setState((String)inbag.get("state"));
        address.setZipCode((String)inbag.get("zipCode"));

        AddressRepository addressRepository = new AddressRepository();
        addressRepository.insert(address);

        addressRepository.find(address.getId());

        outBag.put("id", address.getId());
        outBag.put("street", address.getStreet());
        outBag.put("city", address.getCity());
        outBag.put("state", address.getState());
        outBag.put("zipCode", address.getZipCode());


        return outBag;

    }

    public CDBag getAddress(CDBag inbag) {
        CDBag outBag = new CDBag();
        AddressRepository addressRepository = new AddressRepository();
        Address address = addressRepository.find(Long.valueOf((String) inbag.get("id")));
        outBag.put("id", address.getId());
        outBag.put("street", address.getStreet());
        outBag.put("city", address.getCity());
        outBag.put("state", address.getState());
        outBag.put("zipCode", address.getZipCode());
        return outBag;
    }
}
