package com.sparta.java_02.domain.user.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

  public void save(String username, String password, String email, String phoneNumber) {
    // Logic to save user
    // This could include validation, hashing the password, and saving to a database
    System.out.println("User saved with username: " + username);
  }

//  public UserResponseDto signUp(UserSignUpRequestDto requestDto) {
//    return new UserResponseDto(
//        requestDto.getName(),
//        requestDto.getEmail(),
//        requestDto.getPassword()
//    );
//  }
}
