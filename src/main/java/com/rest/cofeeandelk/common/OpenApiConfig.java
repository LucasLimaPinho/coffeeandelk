package com.rest.cofeeandelk.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI coffeAndElkOpenApi() {

		var info = new Info().title("Coffe and ELK API")
				.description("Open API (Swagger) Documentation for Spring Boot REST API with ElasticSearch")
				.version("1.0.0");

		return new OpenAPI().info(info);

	}

}
