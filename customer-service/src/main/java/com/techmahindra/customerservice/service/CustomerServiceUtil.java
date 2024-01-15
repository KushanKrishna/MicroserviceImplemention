package com.techmahindra.customerservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techmahindra.customerservice.model.CustomerBook;
import com.techmahindra.customerservice.model.LibraryCustomer;
import com.techmahindra.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class CustomerServiceUtil {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RestTemplate restTemplate;

    public List<CustomerBook> getIssuedBookList(String customerId) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        List<CustomerBook> books = mapper.convertValue(restTemplate.getForObject("http://LIBRARY-SERVICE/books/getIssuedBooks?customerId=" + customerId, List.class), new TypeReference<List<CustomerBook>>() {
        });
        return books;
    }

    public String getCustomerId(LibraryCustomer libraryCustomer) {
        String str = "ABCDEFGHIJKLMNOPQRSTUV1234567890WXYZ";
        Random rnd = new Random();
        StringBuilder customerId = new StringBuilder("CUST");
        for (int i = 0; i < 6; i++) {
            int temp = rnd.nextInt(str.length());
            customerId.append(Character.toUpperCase(str.charAt(temp)));
        }
        List<LibraryCustomer> all = this.customerRepository.findAll();
        if (all != null) {
            List<String> collect = all.stream().map(libraryCustomer1 -> libraryCustomer1.getCustomerId()).collect(Collectors.toList());
            if (collect != null && !collect.isEmpty())
                if (collect.contains(customerId.toString())) {
                    return getCustomerId(libraryCustomer);
                } else {
                    return customerId.toString();
                }
        }
        return null;
    }

    public String getCustomerPwd(LibraryCustomer libraryCustomer) {
        String str = "ABCDEFGHIJKLMNOPQRST@!#$*UV1234567890WXYZ";
        Random rnd = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            password.append(str.charAt(rnd.nextInt(str.length())));

        }
        return password.toString().trim();

    }
}
