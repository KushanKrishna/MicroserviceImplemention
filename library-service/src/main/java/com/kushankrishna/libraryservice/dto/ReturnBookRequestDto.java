package com.kushankrishna.libraryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnBookRequestDto {
    String customerId;
    String bookName;
    String ISBN;

}
