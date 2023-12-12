package com.example.sample.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length = 50)
    @Size(max=50)
    private String userName;

    @Column(length = 11)
    @Size(max=11)
    private String mobileNumber;

    public Members (String userName, String mobileNumber) {
        this.userName = userName;
        this.mobileNumber = mobileNumber;
    }
}
