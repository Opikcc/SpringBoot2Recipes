package com.bpn.SpringBootThymeleaf.repository;

import com.bpn.SpringBootThymeleaf.model.Customer;

import java.util.List;

public interface CustomerRepository {

  List<Customer> findAll();
  Customer findById(long id);
  Customer save(Customer customer);
}
