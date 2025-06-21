package com.sparta.java_02.domain.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    /**
     * 특정 사용자의 모든 장바구니 항목 조회
     */
    @Query("SELECT c FROM Cart c JOIN FETCH c.product WHERE c.user.id = :userId")
    List<Cart> findByUserIdWithProduct(@Param("userId") Long userId);

    /**
     * 특정 사용자와 상품에 대한 장바구니 항목 조회
     */
    Optional<Cart> findByUserIdAndProductId(Long userId, Long productId);

    /**
     * 특정 사용자의 장바구니 항목 개수 조회
     */
    long countByUserId(Long userId);

    /**
     * 특정 사용자의 장바구니에서 특정 상품 삭제
     */
    void deleteByUserIdAndProductId(Long userId, Long productId);

    /**
     * 특정 사용자의 모든 장바구니 항목 삭제
     */
    void deleteByUserId(Long userId);
} 