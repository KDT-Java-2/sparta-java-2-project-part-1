package com.sparta.java_02.domain.refund;

import com.sparta.java_02.domain.product.Product;
import com.sparta.java_02.domain.product.ProductRepository;
import com.sparta.java_02.domain.purchase.Purchase;
import com.sparta.java_02.domain.purchase.PurchaseRepository;
import com.sparta.java_02.domain.purchase.PurchaseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RefundService {

    private final RefundRepository refundRepository;
    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;

    /**
     * 환불 요청
     */
    @Transactional
    public Refund requestRefund(Long purchaseId, String reason) {
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new IllegalArgumentException("구매 내역을 찾을 수 없습니다."));

        // 환불 가능성 검증
        if (!purchase.canRequestRefund()) {
            throw new IllegalArgumentException("환불을 요청할 수 없는 구매 상태입니다.");
        }

        // 이미 환불 요청이 있는지 확인
        if (refundRepository.findByPurchaseId(purchaseId).isPresent()) {
            throw new IllegalArgumentException("이미 환불 요청된 구매입니다.");
        }

        // 환불 금액 계산
        BigDecimal refundAmount = purchase.calculateRefundableAmount();
        
        // 환불 요청 생성
        Refund refund = new Refund(purchase, reason, refundAmount);
        refund = refundRepository.save(refund);

        // 구매 상태를 환불 요청으로 변경
        purchase.updateStatus(PurchaseStatus.REFUND_REQUESTED);
        purchaseRepository.save(purchase);

        return refund;
    }

    /**
     * 환불 승인
     */
    @Transactional
    public Refund approveRefund(Long refundId, Long adminId, String adminNotes) {
        Refund refund = refundRepository.findById(refundId)
                .orElseThrow(() -> new IllegalArgumentException("환불 요청을 찾을 수 없습니다."));

        refund.approve(adminId, adminNotes);
        return refundRepository.save(refund);
    }

    /**
     * 환불 거부
     */
    @Transactional
    public Refund rejectRefund(Long refundId, Long adminId, String adminNotes) {
        Refund refund = refundRepository.findById(refundId)
                .orElseThrow(() -> new IllegalArgumentException("환불 요청을 찾을 수 없습니다."));

        refund.reject(adminId, adminNotes);
        refundRepository.save(refund);

        // 구매 상태를 완료로 되돌림
        Purchase purchase = refund.getPurchase();
        purchase.updateStatus(PurchaseStatus.COMPLETED);
        purchaseRepository.save(purchase);

        return refund;
    }

    /**
     * 환불 처리 완료
     */
    @Transactional
    public Refund completeRefund(Long refundId, Long adminId, String adminNotes) {
        Refund refund = refundRepository.findById(refundId)
                .orElseThrow(() -> new IllegalArgumentException("환불 요청을 찾을 수 없습니다."));

        if (!refund.isProcessable()) {
            throw new IllegalArgumentException("처리할 수 없는 환불 상태입니다.");
        }

        // 재고 복구
        Purchase purchase = refund.getPurchase();
        Product product = purchase.getProduct();
        product.increaseStock(purchase.getQuantity());
        productRepository.save(product);

        // 환불 완료
        refund.complete(adminId, adminNotes);
        refundRepository.save(refund);

        // 구매 상태를 환불 완료로 변경
        purchase.updateStatus(PurchaseStatus.REFUNDED);
        purchaseRepository.save(purchase);

        return refund;
    }

    /**
     * 환불 취소 (사용자 요청)
     */
    @Transactional
    public Refund cancelRefund(Long refundId) {
        Refund refund = refundRepository.findById(refundId)
                .orElseThrow(() -> new IllegalArgumentException("환불 요청을 찾을 수 없습니다."));

        refund.cancel();
        refundRepository.save(refund);

        // 구매 상태를 완료로 되돌림
        Purchase purchase = refund.getPurchase();
        purchase.updateStatus(PurchaseStatus.COMPLETED);
        purchaseRepository.save(purchase);

        return refund;
    }

    /**
     * 특정 구매의 환불 내역 조회
     */
    public Refund getRefundByPurchaseId(Long purchaseId) {
        return refundRepository.findByPurchaseId(purchaseId)
                .orElse(null);
    }

    /**
     * 사용자의 환불 내역 조회
     */
    public List<Refund> getUserRefunds(Long userId) {
        return refundRepository.findByPurchaseUserIdOrderByRequestedAtDesc(userId);
    }

    /**
     * 상태별 환불 내역 조회
     */
    public List<Refund> getRefundsByStatus(RefundStatus status) {
        return refundRepository.findByStatusWithPurchaseAndUserOrderByRequestedAtDesc(status);
    }

    /**
     * 처리 대기 중인 환불 건수 조회
     */
    public long getPendingRefundCount() {
        return refundRepository.countPendingRefunds();
    }

    /**
     * 환불 상태 변경 (관리자용)
     */
    @Transactional
    public Refund updateRefundStatus(Long refundId, RefundStatus newStatus, Long adminId, String adminNotes) {
        return switch (newStatus) {
            case UNDER_REVIEW -> {
                Refund refund = refundRepository.findById(refundId)
                        .orElseThrow(() -> new IllegalArgumentException("환불 요청을 찾을 수 없습니다."));
                refund.updateStatus(newStatus, adminId, adminNotes);
                yield refundRepository.save(refund);
            }
            case APPROVED -> approveRefund(refundId, adminId, adminNotes);
            case REJECTED -> rejectRefund(refundId, adminId, adminNotes);
            case COMPLETED -> completeRefund(refundId, adminId, adminNotes);
            default -> throw new IllegalArgumentException("지원하지 않는 상태 변경입니다: " + newStatus);
        };
    }
} 