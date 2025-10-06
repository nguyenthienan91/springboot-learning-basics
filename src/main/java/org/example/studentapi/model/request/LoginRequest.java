package org.example.studentapi.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginRequest {

    @NotEmpty
    String phone;
    @NotEmpty
    String password;
}
