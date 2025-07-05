package com.dogworld.dogdog.member.application;

import com.dogworld.dogdog.global.error.code.ErrorCode;
import com.dogworld.dogdog.global.error.exception.CustomException;
import com.dogworld.dogdog.member.domain.Member;
import com.dogworld.dogdog.member.domain.repository.MemberRepository;
import com.dogworld.dogdog.member.interfaces.dto.request.MemberRequest;
import com.dogworld.dogdog.member.interfaces.dto.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCommandService {
  private final MemberRepository memberRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public MemberResponse createMember(MemberRequest request) {
    validateDuplicateUsernameAndEmail(request);

    Member createdMember = Member.create(request, bCryptPasswordEncoder);
    Member savedMember = memberRepository.save(createdMember);

    return MemberResponse.from(savedMember);
  }

  private void validateDuplicateUsernameAndEmail(MemberRequest request) {
    if(memberRepository.existsByUsername(request.getUsername())) {
      throw new CustomException(ErrorCode.DUPLICATED_USERNAME);
    }

    if(memberRepository.existsByEmail(request.getEmail())) {
      throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
    }
  }
}
