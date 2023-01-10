package com.coredev.operations;

import com.coredev.entity.Account;
import com.coredev.entity.Customer;
import com.coredev.repository.AccountRepository;
import com.coredev.repository.CustomerRepository;
import com.coredev.types.CDBag;

public class AccountOperations {
    public CDBag create(CDBag inBag) {
        CDBag outBag = new CDBag();
        AccountRepository accountRepository = new AccountRepository();
        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer = customerRepository.find((Long) inBag.get("customerId"));

        Account account = new Account();
        // generate random account number
        account.setAccountNumber(String.valueOf((long) (Math.random() * 1000000000)));
        account.setBalance(0.0);

        accountRepository.insert(account);

        customer.getAccounts().add(account);
        customerRepository.update(customer);

        account.setCustomer(customer);
        accountRepository.update(account);

        outBag.put("id", account.getId());
        outBag.put("accountNumber", account.getAccountNumber());
        outBag.put("balance", account.getBalance());
        outBag.put("customerId", account.getCustomer().getId());
        outBag.put("customerName", account.getCustomer().getName());
        return outBag;
    }

}
