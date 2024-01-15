package com.kushankrishna.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueBookReqtDto {

    String customerId;
    String password;
    String bookName;
    String bookAuthor;
    String bookPublisher;

}
