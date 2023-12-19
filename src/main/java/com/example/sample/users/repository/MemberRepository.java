package com.example.sample.users.repository;

import com.example.sample.users.domain.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Members, Long> {
}
