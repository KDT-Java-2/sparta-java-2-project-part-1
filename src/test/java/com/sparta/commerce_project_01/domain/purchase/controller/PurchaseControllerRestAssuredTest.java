package com.sparta.commerce_project_01.domain.purchase.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PurchaseControllerRestAssuredTest {

  @LocalServerPort
  private int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
  }

  void 주문_성공() {
    // given
    String url = "http://localhost:" + port + "/api/purchases";
    String requestBody = """
        {
          "userId": 1,
          "purchaseProducts": [
             {
               "productId": 1,
               "quantity": 10
             }
          ]
        }
        """;
    // when & then
    RestAssured.given().log().all()
        .contentType(ContentType.JSON)
        .body(requestBody)
        .when()
        .post(url)
        .then().log().all()
        .statusCode(200)
        .body("result", IsEqual.equalTo("true"));
  }


}
