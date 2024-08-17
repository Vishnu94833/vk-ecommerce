package com.vkuppan.cp.customer.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vkuppan.cp.customer.dto.CustomerRequest;
import com.vkuppan.cp.customer.dto.CustomerResponse;
import com.vkuppan.cp.customer.repository.CustomerRepository;
import com.vkuppan.cp.customer.service.CustomerService;
import com.vkuppan.cp.customer.util.CustomerUtil;

@Service
public class CustomerServiceImpl implements CustomerService
{
    private final CustomerRepository customerRepository;

    private CustomerUtil customerUtil;

    public CustomerServiceImpl(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    @Override
    public String createCustomer(CustomerRequest customerRequest)
    {
        var customer = customerRepository.save(customerUtil.mapToCustomer(customerRequest));
        return customer.getId();
    }

    @Override
    public String updateCustomer(CustomerRequest customerRequest)
    {
        var updatedCustomer = customerRepository.findById(customerRequest.id())
                .orElseThrow(() -> new RuntimeException("Customer with id " + customerRequest.id() + " not found"));

        customerUtil.mergerCustomer(updatedCustomer, customerRequest);
        customerRepository.save(updatedCustomer);
        return "";
    }

    @Override
    public List<CustomerResponse> findAllCustomers()
    {
        return customerRepository.findAll().stream().map(customerUtil::fromCustomer).collect(Collectors.toList());
    }

    @Override
    public Boolean existsById(String customerId)
    {
        return customerRepository.findById(customerId).isPresent();
    }

    @Override
    public CustomerResponse findById(String customerId)
    {
        return customerRepository.findById(customerId).map(customerUtil::fromCustomer)
                .orElseThrow(() -> new RuntimeException("Customer with id " + customerId + " not found"));
    }

    @Override
    public void deleteCustomer(String customerId)
    {
        customerRepository.deleteById(customerId);
    }
}
