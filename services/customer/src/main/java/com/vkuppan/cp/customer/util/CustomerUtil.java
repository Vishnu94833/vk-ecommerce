package com.vkuppan.cp.customer.util;

import org.apache.commons.lang.StringUtils;

import com.vkuppan.cp.customer.document.Customer;
import com.vkuppan.cp.customer.dto.CustomerRequest;
import com.vkuppan.cp.customer.dto.CustomerResponse;

public class CustomerUtil
{
    public void mergerCustomer(Customer updatedCustomer, CustomerRequest customerRequest)
    {
        if (StringUtils.isNotBlank(customerRequest.firstName())){
            updatedCustomer.setFirstName(customerRequest.firstName());
        }
        if (StringUtils.isNotBlank(customerRequest.lastName())){
            updatedCustomer.setLastName(customerRequest.lastName());
        }
        if (StringUtils.isNotBlank(customerRequest.email())){
            updatedCustomer.setEmail(customerRequest.email());
        }
        if (customerRequest.address() != null){
            updatedCustomer.setAddress(customerRequest.address());
        }
    }

    public Customer mapToCustomer(CustomerRequest customer)
    {
        if (customer == null){
            return null;
        }
        return Customer.builder()
                .id(customer.id())
                .firstName(customer.firstName())
                .lastName(customer.lastName())
                .address(customer.address())
                .email(customer.email())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer){
        return new CustomerResponse(customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress());
    }
}
