package com.kushankrishna.customerservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryCustomer {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String age;
    String occupation;
    String email;
    String phoneNo;
    String customerId;
    String password;
    Double totalOutstanding;
    LocalDate userCreationDate;
    LocalTime userCreationTime;
}
