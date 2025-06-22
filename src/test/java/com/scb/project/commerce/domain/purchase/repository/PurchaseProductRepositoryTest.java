package com.scb.project.commerce.domain.purchase.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.scb.project.commerce.domain.product.entity.Product;
import com.scb.project.commerce.domain.product.repository.ProductRepository;
import com.scb.project.commerce.domain.purchase.entity.Purchase;
import com.scb.project.commerce.domain.purchase.entity.PurchaseProduct;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class PurchaseProductRepositoryTest {

    @Autowired
    PurchaseProductRepository purchaseProductRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("기본 CRUD : 한 건의 상품 주문 조회에 성공합니다.")
    void selectOneSuccess() {
        // when
        PurchaseProduct purchaseProduct = purchaseProductRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("주문 내역이 존재하지 않습니다."));

        // then
        assertThat(purchaseProduct.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("기본 CRUD : 한 건의 주문 내 상품 개수 조회에 성공합니다.")
    void selectAllByPurchaseSuccess() {
        // given
        Purchase purchase = purchaseRepository.findById(2L)
            .orElseThrow(() -> new RuntimeException("주문이 존재하지 않습니다."));

        // when
        List<PurchaseProduct> purchaseProducts = purchaseProductRepository.findAllByPurchase(
            purchase);

        // then
        assertThat(purchaseProducts.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("기본 CRUD : 주문-상품 생성에 성공합니다.")
    void insertSuccess() {
        // given
        Purchase purchase = purchaseRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("주문이 존재하지 않습니다."));

        Product product = productRepository.findByProductName("맥 mini");

        PurchaseProduct savePurchaseProduct = PurchaseProduct.builder()
            .purchase(purchase)
            .product(product)
            .quantity(1)
            .price(product.getPrice())
            .build();

        // when
        purchaseProductRepository.save(savePurchaseProduct);

        // then
        // Id가 계속 늘어나는데 찾아서 에러 발생
//        PurchaseProduct purchaseProduct = purchaseProductRepository.findById(6L)
//            .orElseThrow(() -> new RuntimeException("주문-상품이 존재하지 않습니다."));

        PurchaseProduct purchaseProduct = purchaseProductRepository.findByPurchaseAndProduct(
            purchase, product);

        assertThat(purchaseProduct.getProduct()).isEqualTo(product);
    }
}