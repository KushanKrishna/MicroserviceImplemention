package com.techmahindra.libraryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddLibraryResponseDto {

    String message;
    String status;
    Integer httpStatus;
    Timestamp timestamp;
}
