package com.rest.cofeeandelk.api.server;

import static org.junit.jupiter.api.Assertions.fail;

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
		
		// Código para fazer um mocked HTTP GET REQUEST using webTestClient e a configuração de @SpringBootTest
		// Este código faz um request GET no endpoint "/api/welcome"		
		//Valor esperado para HTTP Status Code - 2xx successfull
		
		webTestClient.get().uri("/api/welcome").exchange().
		expectStatus().is2xxSuccessful().
		expectBody(String.class).
		value(IsEqualIgnoringCase.equalToIgnoringCase("Just give me Coffe & TV, oops, Elastic Stack (ElasticSearch, Logstash and Kibana)!"));
		
	}

	@Test
	void testTime() {
		fail("Not yet implemented");
	}

	@Test
	void testHeaderByAnnotation() {
		fail("Not yet implemented");
	}

	@Test
	void testHeaderByRequest() {
		fail("Not yet implemented");
	}

}
