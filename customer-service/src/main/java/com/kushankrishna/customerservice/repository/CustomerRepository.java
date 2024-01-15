package com.kushankrishna.customerservice.repository;

import com.kushankrishna.customerservice.model.LibraryCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<LibraryCustomer,Long> {
}
