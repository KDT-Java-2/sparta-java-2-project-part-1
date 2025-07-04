package com.dogworld.dogdog.member.domain;

import com.dogworld.dogdog.member.interfaces.dto.request.MemberRequest;
import com.dogworld.dogdog.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 50, nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(length = 100, nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(length = 20, nullable = false, unique = true)
  private String phoneNumber;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private MemberRole role;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private MemberStatus status;

  @Column(nullable = false)
  private boolean agreedTerms;

  @Column(nullable = false)
  private boolean agreedPrivacy;

  @Column(nullable = false)
  private boolean agreedMarketing;

  private LocalDateTime marketingAgreedAt;

  private LocalDateTime deletedAt;

  @Builder
  public Member(String username, String password, String name, String email, String phoneNumber,
      MemberRole role, MemberStatus status, boolean agreedTerms, boolean agreedPrivacy,
      boolean agreedMarketing, LocalDateTime marketingAgreedAt) {
    this.username = username;
    this.password = password;
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.role = (role != null) ? role : MemberRole.MEMBER;
    this.status = (status != null) ? status : MemberStatus.ACTIVE;
    this.agreedTerms = agreedTerms;
    this.agreedPrivacy = agreedPrivacy;
    this.agreedMarketing = agreedMarketing;
    this.marketingAgreedAt = marketingAgreedAt;
  }

  public static Member create(MemberRequest request, BCryptPasswordEncoder passwordEncoder) {
    return Member.builder()
        .username(request.getUsername())
        .password(passwordEncoder.encode(request.getPassword()))
        .name(request.getName())
        .email(request.getEmail())
        .phoneNumber(request.getPhoneNumber())
        .role(request.getRole())
        .agreedTerms(request.isAgreedTerms())
        .agreedPrivacy(request.isAgreedPrivacy())
        .agreedMarketing(request.isAgreedMarketing())
        .marketingAgreedAt(request.isAgreedMarketing() ? LocalDateTime.now() : null)
        .build();
  }

}
