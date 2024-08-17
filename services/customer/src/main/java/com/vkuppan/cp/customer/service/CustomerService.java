package com.vkuppan.cp.customer.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.vkuppan.cp.customer.dto.CustomerRequest;
import com.vkuppan.cp.customer.dto.CustomerResponse;

public interface CustomerService
{
    String createCustomer(CustomerRequest customer);

    String updateCustomer(CustomerRequest customer);

    List<CustomerResponse> findAllCustomers();

    Boolean existsById(String customerId);

    CustomerResponse findById(String customerId);

    void deleteCustomer(String customerId);
}
