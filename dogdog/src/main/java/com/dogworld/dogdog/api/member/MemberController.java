package com.dogworld.dogdog.api.member;

import com.dogworld.dogdog.api.request.MemberRequest;
import com.dogworld.dogdog.api.response.MemberResponse;
import com.dogworld.dogdog.domain.member.MemberService;
import com.dogworld.dogdog.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @PostMapping
  public ResponseEntity<ApiResponse<MemberResponse>> createMember(@RequestBody MemberRequest request) {
    MemberResponse response = memberService.createMember(request);
    return ResponseEntity.ok(ApiResponse.success(response));
  }
}
