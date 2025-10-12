package org.example.studentapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Battery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String Name;
    String description;
    boolean isDeleted = false;

    @ManyToOne
    @JoinColumn (name = "model_id")
    Model model;
}
