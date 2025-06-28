package me.chahyunho.projectweek1;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DataSetupTest {

//  @Autowired
//  UserRepository userRepository;
//  @Autowired
//  ProductRepository productRepository;
//  @Autowired
//  PurchaseRepository purchaseRepository;
//  @Autowired
//  RefundsRepository refundsRepository;
//  @Autowired
//  CartRepository cartRepository;
//
//  @Test
//  void generateTestDataWithBuilder() {
//    List<User> users = new ArrayList<>();
//    for (int i = 1; i <= 30; i++) {
//      User user = userRepository.save(User.builder()
//          .name("user" + i)
//          .email("user" + i + "@example.com")
//          .passwordHash("pass" + i)
//          .build());
//      users.add(user);
//    }
//
//    List<Product> products = new ArrayList<>();
//    for (int i = 1; i <= 50; i++) {
//      Product product = productRepository.save(Product.builder()
//          .name("Product " + i)
//          .stock(1)
//          .price(BigDecimal.valueOf(1000 + i))
//          .build());
//      products.add(product);
//    }
//
//    Random random = new Random();
//
//    for (User user : users) {
//      Product randomProduct = products.get(random.nextInt(products.size()));
//
//      Cart cart = cartRepository.save(Cart.builder()
//          .user(user)
//          .product(randomProduct)
//          .quantity(random.nextInt(5) + 1)
//          .build());
//
//      Purchase purchase = purchaseRepository.save(Purchase.builder()
//          .user(user)
//          .status(PurchaseStatus.COMPLETION)
//          .totalPrice(BigDecimal.valueOf(5000))
//          .build());
//
//      Refunds refund = refundsRepository.save(Refunds.builder()
//          .purchase(purchase)
//          .reason("테스트 환불")
//          .status("REQUESTED")
//          .refundAmount(BigDecimal.valueOf(1000))
//          .build());
//    }
//  }

}
