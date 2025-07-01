package com.dogworld.dogdog.domain.member;

import com.dogworld.dogdog.api.request.MemberRequest;
import com.dogworld.dogdog.api.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  @Transactional
  public MemberResponse createMember(MemberRequest request) {
    Member createdMember = Member.builder()
        .username(request.getUsername())
        .password(request.getPassword())
        .name(request.getName())
        .email(request.getEmail())
        .phoneNumber(request.getPhoneNumber())
        .role(request.getRole())
        .agreedTerms(request.isAgreedTerms())
        .agreedPrivacy(request.isAgreedPrivacy())
        .agreedMarketing(request.isAgreedMarketing())
        .marketingAgreedAt(request.getMarketingAgreedAt())
        .build();

    Member savedMember = memberRepository.save(createdMember);

    return MemberResponse.builder()
        .id(savedMember.getId())
        .username(savedMember.getUsername())
        .email(savedMember.getEmail())
        .name(savedMember.getName())
        .build();
  }
}
