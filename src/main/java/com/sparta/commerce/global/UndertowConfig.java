package com.sparta.commerce.global;

import io.undertow.server.DefaultByteBufferPool;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class UndertowConfig implements
    WebServerFactoryCustomizer<UndertowServletWebServerFactory> {

  @Override
  public void customize(UndertowServletWebServerFactory factory) {
    factory.addDeploymentInfoCustomizers(deploymentInfo -> {
      WebSocketDeploymentInfo webSocketDeploymentInfo = new WebSocketDeploymentInfo();
      webSocketDeploymentInfo.setBuffers(
          new DefaultByteBufferPool(false, 1024)); // 버퍼 풀 크기 1024로 설정
      deploymentInfo.addServletContextAttribute(
          "io.undertow.websockets.jsr.WebSocketDeploymentInfo",
          webSocketDeploymentInfo
      );
    });
  }
}
