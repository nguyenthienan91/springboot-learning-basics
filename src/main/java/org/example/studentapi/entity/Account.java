package org.example.studentapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter

public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @NotEmpty(message="fullname cannot empty!")
    String fullName;
    @Email
            @Column(unique=true)
    String email;
    String gender;
    @NotEmpty(message="password cannot empty!")
    String password;

    @Pattern(
            regexp = "^(03|05|07|08|09|012|016|018|019)[0-9]{8}$",
            message = "Phone invalid!"
    )
    String phone;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }
}
