package com.sparta.commerce_project_01.domain.purchase.service;

import com.sparta.commerce_project_01.domain.purchase.repository.PurchaseRepository;
import com.sparta.commerce_project_01.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseService {

  // 웬만해서는 다른 도메인 서비스 참조는 지양 (순환참조 방지 목적)
  // 다른 도메인 참조가 필요할때는 차라리 레포지토리를 참조해라
  // 서브 서비스는 참조해도 무방(예:PurchaceCancelService)
  private final UserRepository userRepository;
  private final PurchaseRepository purchaseRepository;

}
