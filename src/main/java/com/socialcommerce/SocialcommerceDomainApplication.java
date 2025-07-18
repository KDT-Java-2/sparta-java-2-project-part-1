package com.socialcommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableAsync
@EnableScheduling // 스케줄링 기능을 활성화합니다.
@EnableRedisHttpSession
@SpringBootApplication
public class SocialcommerceDomainApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialcommerceDomainApplication.class, args);
	}

}
