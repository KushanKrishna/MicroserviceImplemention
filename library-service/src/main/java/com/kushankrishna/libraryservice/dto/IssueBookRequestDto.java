package com.kushankrishna.libraryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueBookRequestDto {

    String customerId;
    String bookName;
    String bookAuthor;
    String bookPublisher;


}
