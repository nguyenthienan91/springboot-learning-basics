package org.example.studentapi.model.response;

import lombok.Data;

@Data
public class AccountResponse {
    long id;
    String fullName;
    String email;
    String gender;
    String password;
    String phone;
    String token;
}
