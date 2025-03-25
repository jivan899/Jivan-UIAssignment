package com.infy.RewardPoinntSystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("Blogging Application").version("1.0")
				.description("API documentation for Reward Poin System")
				.contact(new Contact().name("Jivan Mahajan").url("https://google.com")
						.email("jivan.mahajan37@gmail.com"))
				.license(new License().name("License of API").url("https://google.com")));
	}

}
