package me.chahyunho.projectweek1.domain.cart.repository;

import java.util.List;
import me.chahyunho.projectweek1.domain.cart.entity.Cart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@SpringBootTest
class CartRepositoryTest {

  @Autowired
  private CartRepository cartRepository;

  @Test
  void findAllCarts() {
    List<Cart> selectedList = cartRepository.findAll();

    for (Cart cart : selectedList) {
      System.out.println("user    id :  " + cart.getUser().getId());
      System.out.println("product id :  " + cart.getProduct().getId());
      System.out.println("----------------------------------------------");
    }


  }

}