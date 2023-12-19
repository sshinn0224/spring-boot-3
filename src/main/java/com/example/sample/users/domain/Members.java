package com.example.sample.users.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=64)
    @Size(max=64)
    private String userName;

    @Column(length=11)
    @Size(max=11)
    private String mobileNumber;

    private String password;

    @Email
    private String email;

    public Members (String userName, String mobileNumber, String password, String email) {
        this.userName = userName;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.email = email;
    }

}
