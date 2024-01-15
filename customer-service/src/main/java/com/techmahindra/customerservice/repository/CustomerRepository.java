package com.techmahindra.customerservice.repository;

import com.techmahindra.customerservice.model.LibraryCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<LibraryCustomer,Long> {
}
