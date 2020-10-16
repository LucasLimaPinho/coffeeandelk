package com.rest.cofeeandelk.api.server;


import java.time.LocalTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class DefaultRestApi {

	@GetMapping(value = "/welcome")
	public String welcome() {
		System.out.println(StringUtils.join("Just give me"," coffee"," and"," TV"));
		return "Just give me Coffe & TV, oops, Elastic Stack (ElasticSearch, Logstash and Kibana)!";
	}
	
	@GetMapping(value = "/time")
	public String time() {
		return LocalTime.now().toString();
	}
	
	@GetMapping(value = "/header-one")
	public String headerByAnnotation(@RequestHeader(name="User-Agent") String headerUserAgent,
			@RequestHeader(name="coffee-and-elk") String headerCoffeeAndELK) {
		
		return "User-agent: " + headerUserAgent + ", CoffeAndELK :" + headerCoffeeAndELK;
		
		
		
	}

}
