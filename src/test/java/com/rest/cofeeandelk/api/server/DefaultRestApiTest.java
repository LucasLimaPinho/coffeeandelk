package com.rest.cofeeandelk.api.server;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalTime;

import org.hamcrest.text.IsEqualIgnoringCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;



@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DefaultRestApiTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testWelcome() {

		// Código para fazer um mocked HTTP GET REQUEST using webTestClient e a
		// configuração de @SpringBootTest
		// Este código faz um request GET no endpoint "/api/welcome"
		// Valor esperado para HTTP Status Code - 2xx successfull

		webTestClient.get().uri("/api/welcome").exchange().expectStatus().is2xxSuccessful().expectBody(String.class)
				.value(IsEqualIgnoringCase.equalToIgnoringCase(
						"Just give me Coffe & TV, oops, Elastic Stack (ElasticSearch, Logstash and Kibana)!"));

	}

	@Test
	void testTime() {
		webTestClient.get().uri("/api/time").exchange().expectBody(String.class).value(v -> isCorrectTime(v));

	}

	private Object isCorrectTime(String v) {
		
		var responseLocalTime = LocalTime.parse(v);
		var now = LocalTime.now();
		var nowMinus30Seconds = now.minusSeconds(30);
		
		assertTrue(responseLocalTime.isAfter(nowMinus30Seconds) && responseLocalTime.isBefore(now));
		
		return responseLocalTime;
		
	
	}

	@Test
	void testHeaderByAnnotation() {
		
		var headerOne = "Spring Boot Test";
		var headerTwo = "Spring Boot Test with Coffee-and-ELK";
		
		webTestClient.get().uri("/api/header-one").header("User-agent", headerOne)
		.header("Coffee-and-Elk", headerTwo).exchange().expectBody(String.class)
		.value(v -> {
			assertTrue(v.contains(headerOne));
			assertTrue(v.contains(headerTwo));
		});
		
	}

}
