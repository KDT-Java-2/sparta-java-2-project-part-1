package com.dogworld.dogdog.member.domain.repository;

import com.dogworld.dogdog.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);
}
