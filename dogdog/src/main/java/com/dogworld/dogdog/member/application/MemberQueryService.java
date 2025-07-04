package com.dogworld.dogdog.member.application;

import com.dogworld.dogdog.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryService {
  private final MemberRepository memberRepository;

}
