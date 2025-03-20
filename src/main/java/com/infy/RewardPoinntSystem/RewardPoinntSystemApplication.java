package com.infy.RewardPoinntSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EntityScan(basePackages = "com.infy.RewardPoinntSystem.entity")
@EnableJpaRepositories(basePackages = "com.infy.RewardPoinntSystem.repository")
@EnableWebMvc
public class RewardPoinntSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardPoinntSystemApplication.class, args);
		System.out.println("App is runing");
	}

}
