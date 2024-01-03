package com.example.sample.members.service;

import com.example.sample.members.domain.Member;
import com.example.sample.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByUserName(username)
                .map(this::createUser)
                .orElseThrow(
                        () -> new UsernameNotFoundException(username + "을 찾을 수 없습니다.")
                );

    }

    private User createUser(Member member) {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        member.getAuthorityList().stream().map(item -> grantedAuthorityList.add(
                new SimpleGrantedAuthority(item.toString())));
        return new User(member.getUserName(), member.getPassword(), grantedAuthorityList);
    }
}
