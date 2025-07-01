package com.dogworld.dogdog.domain.member;

import com.dogworld.dogdog.api.member.request.MemberRequest;
import com.dogworld.dogdog.api.member.response.MemberResponse;
import com.dogworld.dogdog.global.error.code.ErrorCode;
import com.dogworld.dogdog.global.error.exception.CustomException;
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
