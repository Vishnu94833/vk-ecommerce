package com.vkuppan.cp.customer.dto;

import com.vkuppan.cp.customer.document.Address;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
)
{
}
