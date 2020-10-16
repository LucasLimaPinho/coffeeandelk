package com.rest.cofeeandelk.entity;

import java.time.LocalDate;
import java.util.List;

// Car.java é o nosso POJO. Ele será transformado em JSON através de serialization já presente com Jackson no
// Spring Boot framework. POJO é Plain Old Java Object e não deve possuir business logic - somente setters, getters,
// toString override, hashcode, etc.

// Para construir nossa JSON Response, iremos no basear no modelo do POJO Car.java
// Para ser serializado/marshealizado - a classe deve ser pública

public class Car {

	private List<String> additionalFeatures;

	private boolean available;

	private String brand;

	private String color;

	private LocalDate firstReleaseDate;

	private int price;

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

	public Car(String brand, String color, String type) {
		super();
		this.brand = brand;
		this.color = color;
		this.type = type;
	}
	// Gere automaticamente setters and getters com shortcut ALF+SHIFT+S
	// Também precisamos do método toString() para ser utilizado no HTTP method
	// POST;

	// Para o unmarshalling ocorrer, obrigatoriamente devemos ter um 'construtor
	// vazio' - no-argumento constructor.

	public List<String> getAdditionalFeatures() {
		return additionalFeatures;
	}

	public String getBrand() {
		return brand;
	}

	public String getColor() {
		return color;
	}

	public LocalDate getFirstReleaseDate() {
		return firstReleaseDate;
	}

	public int getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}

	// Gerar construtor padrão de um POJO - ALT+SHIFT+S
	// ALT+SHIFT+S também permite realizar o "Sort Members" para melhor readability
	// do código
	// ALT+SHIFT+F no Eclipse permite corrigir identação

	public boolean isAvailable() {
		return available;
	}

	public void setAdditionalFeatures(List<String> additionalFeatures) {
		this.additionalFeatures = additionalFeatures;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setFirstReleaseDate(LocalDate firstReleaseDate) {
		this.firstReleaseDate = firstReleaseDate;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Car [available=" + available + ", brand=" + brand + ", color=" + color + ", firstReleaseDate="
				+ firstReleaseDate + ", price=" + price + ", type=" + type + "]";
	}

}
