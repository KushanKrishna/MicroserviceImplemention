package com.techmahindra.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserFailedResponseDto {

    String message;
    String status;
    Integer httpStatus;
    Timestamp timestamp;
}
