package com.sparta.javamarket.domain.user.repository;

import com.sparta.javamarket.common.enums.UserRoleStatus;
import com.sparta.javamarket.domain.user.entity.User;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

//@Transactional
@SpringBootTest
public class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  void 회원가입 (){
    User user = userRepository.save(User.builder()
        .email("seller@aaaa.com")
        .password("111111")
        .name("판매자")
        .nickname("판매자A")
        .address("경기도 수원")
        .phone("010-1234-1234")
        .role(UserRoleStatus.SELLER)
        .build());
  }

  @Test
  void 전체회원조회 (){
    List<User> users = userRepository.findAll();

    for( User user: users){
      String name = user.getName();
      String nickname = user.getNickname();
      UserRoleStatus role = user.getRole();

      System.out.println("이름 : " + name + " / " + nickname + " / " + role);
    }
  }

  @Test
  void 특정회원조회 () {
    User user = userRepository.findFirstByEmail("ljs7908@gmail.com");

    System.out.println(user.getEmail());

    List<User> users = userRepository.findFirstByName("판매자");

    for(User a : users){
      String name = a.getName();
      String email = a.getEmail();
      UserRoleStatus role = user.getRole();

      System.out.println("이름 : " + name + " / " + a + " / " + role);

    }

    List<User> users1 = userRepository.findByName("판매자");

    for(User a : users1){
      String name = a.getName();
      String email = a.getEmail();
      UserRoleStatus role = user.getRole();

      System.out.println("이름 : " + name + " / " + a + " / " + role);

    }

  }

//  @Transactional
  @Test
  void 회원수정(){
    User user = userRepository.findFirstByEmail("ljs7908@gmail.com");

    System.out.println("@@ : " + user.getNickname());

    user.setNickname("회사식당");

    userRepository.save(user);

    user = userRepository.findFirstByEmail("ljs7908@gmail.com");

    System.out.println("@@ : " + user.getNickname());

  }


}
