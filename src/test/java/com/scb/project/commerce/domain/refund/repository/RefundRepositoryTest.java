package com.scb.project.commerce.domain.refund.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.scb.project.commerce.common.enums.RefundStatus;
import com.scb.project.commerce.domain.purchase.entity.Purchase;
import com.scb.project.commerce.domain.purchase.repository.PurchaseRepository;
import com.scb.project.commerce.domain.refund.entity.Refund;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class RefundRepositoryTest {

    @Autowired
    RefundRepository refundRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Test
    @DisplayName("기본 CRUD : 한 건의 환불 조회에 성공합니다.")
    void selectOneRefundSuccess() {
        // when
        Refund refund = refundRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("환불 내역이 없습니다."));

        // then
        assertThat(refund.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("기본 CRUD : 한 주문의 환불 건 모두 조회에 성공하였습니다.")
    void selectAllRefundSuccess() {
        // given
        Purchase purchase = purchaseRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("주문이 존재하지 않습니다."));

        // when
        List<Refund> refunds = refundRepository.findAllByPurchase(purchase);

        // then
        assertThat(refunds.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("기본 CRUD : 환불 생성에 성공합니다.")
    void insertRefundSuccess() {
        // given
        Purchase purchase = purchaseRepository.findById(2L)
            .orElseThrow(() -> new RuntimeException("주문이 존재하지 않습니다."));

        // when
        Refund saveRefund = Refund.builder()
            .purchase(purchase)
            .reason("돈 안내 배째")
            .status(RefundStatus.REJECTED)
            .build();

        refundRepository.save(saveRefund);

        // then
        List<Refund> refund = refundRepository.findAllByPurchase(purchase);
        assertThat(refund.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("기본 CRUD : 환불 수정에 성공하였습니다.")
    void updateRefundStatusSuccess() {
        // given
        Refund updateRefund = refundRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("환불 내역이 존재하지 않습니다."));

        // when
        updateRefund.setStatus(RefundStatus.PROCESSING);

        refundRepository.save(updateRefund);

        // then
        Refund refund = refundRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("환불 내역이 존재하지 않습니다."));

        assertThat(refund.getStatus()).isEqualTo(RefundStatus.PROCESSING);
    }

    @Test
    @DisplayName("기본 CRUD : 환불 삭제에 성공하였습니다.")
    void deleteRefundSuccess() {
        // given
        Refund deleteRefund = refundRepository.findById(2L)
            .orElseThrow(() -> new RuntimeException("환불 내역이 존재하지 않습니다."));

        // when
        refundRepository.delete(deleteRefund);

        // then
        boolean isExists = refundRepository.existsById(2L);

        assertThat(isExists).isFalse();
    }
}