package com.kushankrishna.libraryworkerservice.service;

import com.kushankrishna.libraryworkerservice.dto.CustomerResponseDto;
import com.kushankrishna.libraryworkerservice.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class LibraryWorkerServiceUtil {


    public CustomerResponseDto buildCustomerListResponse(Customer customer) {
        return CustomerResponseDto.builder()
                .age(customer.getAge())
                .email(customer.getEmail())
                .name(customer.getName())
                .customerId(customer.getCustomerId())
                .occupation(customer.getOccupation())
                .phoneNo(customer.getPhoneNo())
                .totalOutstanding(customer.getTotalOutstanding())
                .userCreationDate(customer.getUserCreationDate())
                .userCreationTime(customer.getUserCreationTime())
                .build();
    }
}
