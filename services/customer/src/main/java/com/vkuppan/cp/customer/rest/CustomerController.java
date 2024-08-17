package com.vkuppan.cp.customer.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vkuppan.cp.customer.dto.CustomerRequest;
import com.vkuppan.cp.customer.dto.CustomerResponse;
import com.vkuppan.cp.customer.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController
{
    private final CustomerService customerService;

    CustomerController(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest customer)
    {
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @PutMapping
    public ResponseEntity<String> updateCustomer(@RequestBody @Valid CustomerRequest customer)
    {
        return ResponseEntity.ok(customerService.updateCustomer(customer));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll()
    {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable("id") String customerId)
    {
        return ResponseEntity.ok(customerService.existsById(customerId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable("id") String customerId)
    {
        return ResponseEntity.ok(customerService.findById(customerId));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String customerId)
    {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }

}
