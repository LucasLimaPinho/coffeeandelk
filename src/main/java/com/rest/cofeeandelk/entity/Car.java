package com.rest.cofeeandelk.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

// Car.java é o nosso POJO. Ele será transformado em JSON através de serialization já presente com Jackson no
// Spring Boot framework. POJO é Plain Old Java Object e não deve possuir business logic - somente setters, getters,
// toString override, hashcode, etc.

// Para construir nossa JSON Response, iremos no basear no modelo do POJO Car.java
// Para ser serializado/marshealizado - a classe deve ser pública

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Car {

	// JsonInclude estabelece condições para se o field será exposto ou não da response da request da API.
	
	@JsonInclude(value = Include.NON_EMPTY)
	private List<String> additionalFeatures;
	
	private boolean available;

	private String brand;

	private String color;

	@JsonUnwrapped
	private Engine engine;

	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Jakarta")
	private LocalDate firstReleaseDate;

	private int price;

	@JsonInclude(value = Include.NON_EMPTY)
	private String secretFeature;

	private List<Tire> tires;

	private String type;

	public Car() {

	}
	
	public Car(boolean available, String brand, String color, LocalDate firstReleaseDate, int price, String type) {
		super();
		this.available = available;
		this.brand = brand;
		this.color = color;
		this.firstReleaseDate = firstReleaseDate;
		this.price = price;
		this.type = type;
	}
	
	public Car(List<String> additionalFeatures, boolean available, String brand, String color,
			LocalDate firstReleaseDate, int price, String type, Engine engine, List<Tire> tires) {
		super();
		this.additionalFeatures = additionalFeatures;
		this.available = available;
		this.brand = brand;
		this.color = color;
		this.firstReleaseDate = firstReleaseDate;
		this.price = price;
		this.type = type;
		this.engine = engine;
		this.tires = tires;
	}

	public Car(List<String> additionalFeatures, String secretFeature, boolean available, String brand, String color,
			Engine engine, LocalDate firstReleaseDate, int price, List<Tire> tires, String type) {
		super();
		this.additionalFeatures = additionalFeatures;
		this.secretFeature = secretFeature;
		this.available = available;
		this.brand = brand;
		this.color = color;
		this.engine = engine;
		this.firstReleaseDate = firstReleaseDate;
		this.price = price;
		this.tires = tires;
		this.type = type;
	}

	public Car(String brand, String color, String type) {
		super();
		this.brand = brand;
		this.color = color;
		this.type = type;
	}
	// Gere automaticamente setters and getters com shortcut ALF+SHIFT+S
	// Também precisamos do método toString() para ser utilizado no HTTP method
	// POST;

	public List<String> getAdditionalFeatures() {
		return additionalFeatures;
	}

	public String getBrand() {
		return brand;
	}

	public String getColor() {
		return color;
	}

	public Engine getEngine() {
		return engine;
	}

	public LocalDate getFirstReleaseDate() {
		return firstReleaseDate;
	}

	public int getPrice() {
		return price;
	}

	// Para o unmarshalling ocorrer, obrigatoriamente devemos ter um 'construtor
	// vazio' - no-argumento constructor.

	public String getSecretFeature() {
		return secretFeature;
	}

	public List<Tire> getTires() {
		return tires;
	}

	public String getType() {
		return type;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAdditionalFeatures(List<String> additionalFeatures) {
		this.additionalFeatures = additionalFeatures;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	// Gerar construtor padrão de um POJO - ALT+SHIFT+S
	// ALT+SHIFT+S também permite realizar o "Sort Members" para melhor readability
	// do código
	// ALT+SHIFT+F no Eclipse permite corrigir identação

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public void setFirstReleaseDate(LocalDate firstReleaseDate) {
		this.firstReleaseDate = firstReleaseDate;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setSecretFeature(String secretFeature) {
		this.secretFeature = secretFeature;
	}

	public void setTires(List<Tire> tires) {
		this.tires = tires;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Car [additionalFeatures=" + additionalFeatures + ", available=" + available + ", brand=" + brand
				+ ", color=" + color + ", engine=" + engine + ", firstReleaseDate=" + firstReleaseDate + ", price="
				+ price + ", secretFeature=" + secretFeature + ", tires=" + tires + ", type=" + type + "]";
	}

}
