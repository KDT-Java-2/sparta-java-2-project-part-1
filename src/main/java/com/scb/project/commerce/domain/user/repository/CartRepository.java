package com.scb.project.commerce.domain.user.repository;

import com.scb.project.commerce.domain.product.entity.Product;
import com.scb.project.commerce.domain.user.entity.Cart;
import com.scb.project.commerce.domain.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    /**
     * 사용자 정보로 장바구니 찾는 메서드
     *
     * @param user 사용자 객체
     * @return 장바구니 객체
     */
    List<Cart> findAllByUser(User user);

    /**
     * 사용자, 상품 정보로 장바구니 삭제하는 메서드
     *
     * @param user    사용자 객체
     * @param product 장바구니 객체
     */
    void deleteByUserAndProduct(User user, Product product);

    /**
     * 사용자, 상품 정보를 가진 장바구니 있는지 확인하는 메서드
     *
     * @param user    사용자 객체
     * @param product 상품 객체
     * @return 불린 값 (true / false)
     */
    boolean existsByUserAndProduct(User user, Product product);
}
