package com.springframework.api.v1.mapper;

import com.springframework.api.v1.model.CustomerDTO;
import com.springframework.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    public static final String FIRSTNAME = "Daniel";
    public static final String LASTNAME = "Byrne";
    public static final long ID = 1L;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() {

        //given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRSTNAME);
        customer.setLastName(LASTNAME);

        //when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //then
        assertEquals(Long.valueOf(ID), customerDTO.getId());
        assertEquals(FIRSTNAME, customerDTO.getFirstName());
        assertEquals(LASTNAME, customerDTO.getLastName());
    }
}