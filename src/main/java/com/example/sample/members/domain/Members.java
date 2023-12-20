package com.example.sample.members.domain;


import com.example.sample.common.utils.CryptoConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=64)
    @Size(max=64)
    @Convert(converter = CryptoConverter.class)
    private String userName;

    @Column(length=64)
    @Size(max=64)
    @Convert(converter = CryptoConverter.class)
    private String mobileNumber;

    @Convert(converter = CryptoConverter.class)
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
