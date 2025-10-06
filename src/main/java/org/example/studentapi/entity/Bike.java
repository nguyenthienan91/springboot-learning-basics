package org.example.studentapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bike {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long id;
    String name;
    String description;

    @ManyToOne
    @JoinColumn(name="account_id")
    @JsonIgnore
    Account account;
}
