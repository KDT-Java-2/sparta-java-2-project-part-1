package com.sparta.sparta_java_2_project_part_1.domain.purchase.repository;

import com.sparta.sparta_java_2_project_part_1.domain.purchase.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
  // JpaRepository는 기본적인 CRUD 메서드를 제공하므로 추가적인 메서드는 필요하지 않습니다.
  // 필요한 경우, 사용자 정의 쿼리 메서드를 추가할 수 있습니다.

}
