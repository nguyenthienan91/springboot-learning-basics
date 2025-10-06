package org.example.studentapi.model;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Student {
    private int id;
    private String name;
    @Pattern(regexp="SE\\d{6}", message = "Code must be following format SExxxxxx!")
    private String code;
}

//ORM: Object Relationship Mapping => Data JPA