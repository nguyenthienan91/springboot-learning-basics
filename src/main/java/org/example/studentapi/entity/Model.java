package org.example.studentapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Model {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    String description;
    Date releaseAt;
    boolean is_deleted = false;

    @OneToMany(mappedBy = "model")
    List<Bike> bikes;

    @OneToMany(mappedBy = "model")
    @JsonIgnore
    List<Battery> batteries;
}
