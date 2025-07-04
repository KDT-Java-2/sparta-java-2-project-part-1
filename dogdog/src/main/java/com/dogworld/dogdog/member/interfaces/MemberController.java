package com.dogworld.dogdog.member.interfaces;

import com.dogworld.dogdog.member.application.MemberCommandService;
import com.dogworld.dogdog.member.interfaces.dto.request.MemberRequest;
import com.dogworld.dogdog.member.interfaces.dto.response.MemberResponse;
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

  private final MemberCommandService memberCommandService;

  @PostMapping
  public ResponseEntity<ApiResponse<MemberResponse>> createMember(@RequestBody MemberRequest request) {
    MemberResponse response = memberCommandService.createMember(request);
    return ResponseEntity.ok(ApiResponse.success(response));
  }
}
