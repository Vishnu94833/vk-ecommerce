package com.vkuppan.cp.customer.document;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Valid
public class Address
{
    private String street;
    private String houseNumber;
    private String city;
    private String state;
    private String zip;
}
