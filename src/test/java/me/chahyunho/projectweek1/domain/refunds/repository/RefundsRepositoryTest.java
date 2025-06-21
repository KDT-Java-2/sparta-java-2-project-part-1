package me.chahyunho.projectweek1.domain.refunds.repository;

import java.util.List;
import me.chahyunho.projectweek1.domain.purchase.entity.Purchase;
import me.chahyunho.projectweek1.domain.purchase.repository.PurchaseRepository;
import me.chahyunho.projectweek1.domain.refunds.entity.Refunds;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class RefundsRepositoryTest {

  @Autowired
  private RefundsRepository refundsRepository;

  @Autowired
  private PurchaseRepository purchaseRepository;

  @Test
  void findAllRefunds() {
    
    // 연관관계의 상태 엔티티안에서의 또 다른 엔티티를 조회할시 n + 1 이슈 발생으로 2개를 FETCH 했는데 이게 맞는지 모르겠다.
    List<Refunds> refunds = refundsRepository.findAllWithPurchaseAndUser();

    for (Refunds refund : refunds) {
      System.out.println("refund purchase id : " + refund.getPurchase().getId());
      System.out.println("refund user     id : " + refund.getPurchase().getUser().getId());
    }
  }

  @Test
  void findAllPurchase() {
    List<Purchase> purchases = purchaseRepository.findAll();

    for (Purchase purchase : purchases) {
      System.out.println("purchase      id : " + purchase.getId());
      System.out.println("purchase user id : " + purchase.getUser().getId());
    }
  }
}