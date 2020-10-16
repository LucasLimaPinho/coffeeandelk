package com.rest.cofeeandelk.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

// Car.java � o nosso POJO. Ele ser� transformado em JSON atrav�s de serialization j� presente com Jackson no
// Spring Boot framework. POJO � Plain Old Java Object e n�o deve possuir business logic - somente setters, getters,
// toString override, hashcode, etc.

// Para construir nossa JSON Response, iremos no basear no modelo do POJO Car.java
// Para ser serializado/marshealizado - a classe deve ser p�blica

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Car {

	private List<String> additionalFeatures;

	private boolean available;

	private String brand;

	private String color;

	private Engine engine;

	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Jakarta")
	private LocalDate firstReleaseDate;

	private int price;
	
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

	public Car(String brand, String color, String type) {
		super();
		this.brand = brand;
		this.color = color;
		this.type = type;
	}
	// Gere automaticamente setters and getters com shortcut ALF+SHIFT+S
	// Tamb�m precisamos do m�todo toString() para ser utilizado no HTTP method
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

	// Para o unmarshalling ocorrer, obrigatoriamente devemos ter um 'construtor
	// vazio' - no-argumento constructor.

	public LocalDate getFirstReleaseDate() {
		return firstReleaseDate;
	}

	public int getPrice() {
		return price;
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

	// Gerar construtor padr�o de um POJO - ALT+SHIFT+S
	// ALT+SHIFT+S tamb�m permite realizar o "Sort Members" para melhor readability
	// do c�digo
	// ALT+SHIFT+F no Eclipse permite corrigir identa��o

	public void setAvailable(boolean available) {
		this.available = available;
	}

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

	public void setTires(List<Tire> tires) {
		this.tires = tires;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Car [additionalFeatures=" + additionalFeatures + ", available=" + available + ", brand=" + brand
				+ ", color=" + color + ", firstReleaseDate=" + firstReleaseDate + ", price=" + price + ", type=" + type
				+ ", engine=" + engine + ", tires=" + tires + "]";
	}

}
