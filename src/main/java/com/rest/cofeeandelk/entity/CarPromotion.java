package com.rest.cofeeandelk.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

// Como iremos salvar este Bean no Elasticsearch, ele deve possuir a anotação @Document do Spring Data Elasticsearch
// A anotação @Document deve vir acompanhada do indexName

@Document(indexName = "car-promotion")
public class CarPromotion {

	private String description;
	
	@Id
	private String id;
	
	private String type;
	
	public CarPromotion() {
		
	}

	public CarPromotion(String description, String id, String type) {
		super();
		this.description = description;
		this.id = id;
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}
}
