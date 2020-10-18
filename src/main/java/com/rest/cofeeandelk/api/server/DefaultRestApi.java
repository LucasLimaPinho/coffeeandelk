package com.rest.cofeeandelk.api.server;

import java.time.LocalTime;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class DefaultRestApi {

	@GetMapping(value = "/welcome")
	public String welcome() {
		System.out.println(StringUtils.join("Just give me", " coffee", " and", " TV"));
		return "Just give me Coffe & TV, oops, Elastic Stack (ElasticSearch, Logstash and Kibana)!";
	}

	@GetMapping(value = "/time")
	public String time() {
		return LocalTime.now().toString();
	}

	@GetMapping(value = "/header-one")
	public String headerByAnnotation(@RequestHeader(name = "User-Agent", required = false) String headerUserAgent,
			@RequestHeader(name = "coffee-and-elk", required = false) String headerCoffeeAndELK) {

		return "User-agent: " + headerUserAgent + ", CoffeAndELK :" + headerCoffeeAndELK;

	}

	@GetMapping(value = "/header-two")
	public String headerByRequest(ServerHttpRequest request) {
		return "User-agent : " + request.getHeaders().getValuesAsList("User-Agent") + ", CoffeeAndELK :"
				+ request.getHeaders().getValuesAsList("coffee-and-elk");
	}

	@GetMapping(value = "/random-error")
	public ResponseEntity<String> randomError() {

		int remainder = new Random().nextInt() % 5;
		var body = "Kibana";

		switch (remainder) {
		case 0:
			return ResponseEntity.ok().body(body);
		case 1:
		case 2:
			return ResponseEntity.badRequest().body(body);
		default:
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);

		}
	}

}
