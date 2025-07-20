package com.sparta.spartajava2projectpart1.domain.cart.repository;

import com.sparta.spartajava2projectpart1.domain.cart.entity.Cart;
import com.sparta.spartajava2projectpart1.domain.category.entity.Category;
import com.sparta.spartajava2projectpart1.domain.category.repository.CategoryRepository;
import com.sparta.spartajava2projectpart1.domain.product.entity.Product;
import com.sparta.spartajava2projectpart1.domain.product.repository.ProductRepository;
import com.sparta.spartajava2projectpart1.domain.user.entity.User;
import com.sparta.spartajava2projectpart1.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@SpringBootTest
@Transactional
class CartRepositoryTest {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void save() {

        User user = User.builder()
                .name("test")
                .email("test@test.com")
                .passwordHash("123456")
                .agree(true)
                .thirdAgree(true)
                .marketing(false)
                .build();

        User savedUser = userRepository.save(user);

        Category category = Category.builder()
                .name("category").build();

        Category savedCategory = categoryRepository.save(category);

        Product product = Product.builder()
                .name("product")
                .category(savedCategory)
                .description("description")
                .price(BigDecimal.valueOf(1000))
                .stock(10)
                .build();

        Product savedProduct = productRepository.save(product);

        Cart cart = Cart.builder()
                .user(savedUser)
                .quantity(10)
                .product(savedProduct)
                .build();
        cartRepository.save(cart);
    }
}