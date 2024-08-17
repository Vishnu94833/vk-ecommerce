package com.vkuppan.cp.customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vkuppan.cp.customer.document.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String>
{
}
