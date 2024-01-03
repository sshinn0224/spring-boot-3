package com.example.sample.members.domain;

import com.example.sample.common.types.AuthTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorityId;

    private String userName;

    @Enumerated(EnumType.STRING)
    private AuthTypes authType;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Member member;

    public Authority(String userName, AuthTypes authType, Member member) {
        this.userName = userName;
        this.authType = authType;
        this.member = member;
    }
}
