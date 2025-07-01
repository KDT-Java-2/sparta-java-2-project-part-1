package com.dogworld.dogdog.domain.member;

import com.dogworld.dogdog.api.request.MemberRequest;
import com.dogworld.dogdog.api.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Transactional
  public MemberResponse createMember(MemberRequest request) {
    Member createdMember = Member.create(request, bCryptPasswordEncoder);
    Member savedMember = memberRepository.save(createdMember);

    return MemberResponse.builder()
        .id(savedMember.getId())
        .username(savedMember.getUsername())
        .email(savedMember.getEmail())
        .name(savedMember.getName())
        .build();
  }


}
