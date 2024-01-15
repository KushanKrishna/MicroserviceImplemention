package com.techmahindra.libraryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBookResponseDto {

    String message;
    String status;
    Integer httpStatus;
    Timestamp timestamp;
}
