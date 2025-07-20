package com.sparta.commerce.domain.user.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.commerce.domain.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester.MockMvcRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper = new ObjectMapper();

  @Test
  void 회원_가입_성공() throws Exception {
    User testUser = new User();
    ReflectionTestUtils.setField(testUser, "id", 5L);
    ReflectionTestUtils.setField(testUser, "name", "");
    ReflectionTestUtils.setField(testUser, "email", "test1@test.com");
    ReflectionTestUtils.setField(testUser, "passwordHash", "hashedPassword");

    String requestBody = objectMapper.writeValueAsString(testUser);
    System.out.println("Test Request Body:" + requestBody);

    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/users")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody)
            .accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(true)
    );
 }

}