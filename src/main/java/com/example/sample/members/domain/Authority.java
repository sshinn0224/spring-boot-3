package com.example.sample.members.domain;

import com.example.sample.members.types.AuthType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorityId;

    @Enumerated(EnumType.STRING)
    private AuthType authType;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Member member;

    public Authority(AuthType authType, Member member) {
        this.authType = authType;
        this.member = member;
    }
}
