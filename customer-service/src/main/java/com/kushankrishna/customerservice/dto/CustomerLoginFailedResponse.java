package com.kushankrishna.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerLoginFailedResponse {
    String message;
    String status;
    Integer httpStatus;
    Timestamp timestamp;
}
