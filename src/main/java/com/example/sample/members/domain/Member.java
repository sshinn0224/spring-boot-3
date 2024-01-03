package com.example.sample.members.domain;


import com.example.sample.common.types.AuthTypes;
import com.example.sample.common.utils.CryptoConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length=64)
    @Size(max=64)
    @Email
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

    private String gender;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Authority> authorityList;

    public Member(String userName, String mobileNumber, String password, String email, String gender, List<Authority> authorityList) {
        this.userName = userName;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.authorityList = authorityList;
    }
}
