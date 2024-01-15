package com.techmahindra.libraryworkerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryWorker {

    String name;
    String age;
    String email;
    String phoneNo;
    String employeeId;
    String password;
    Double salary;

}
