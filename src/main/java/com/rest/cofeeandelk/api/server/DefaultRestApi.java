package com.rest.cofeeandelk.api.server;


import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class DefaultRestApi {

	@RequestMapping(value = "/welcome")
	public String welcome() {
		System.out.println(StringUtils.join("Just give me"," coffee"," and"," TV"));
		return "Just give me Coffe & TV, oops, Elastic Stack (ElasticSearch, Logstash and Kibana)!";
	}

}
