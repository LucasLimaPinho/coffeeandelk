package com.rest.cofeeandelk.entity;

// Car.java � o nosso POJO. Ele ser� transformado em JSON atrav�s de serialization j� presente com Jackson no
// Spring Boot framework. POJO � Plain Old Java Object e n�o deve possuir business logic - somente setters, getters,
// toString override, hashcode, etc.

// Para construir nossa JSON Response, iremos no basear no modelo do POJO Car.java
// Para ser serializado/marshealizado - a classe deve ser p�blica

public class Car {

	private String brand;
	private String color;
	private String type;

	// Gerar construtor padr�o de um POJO - ALT+SHIFT+S
	// ALT+SHIFT+S tamb�m permite realizar o "Sort Members" para melhor readability do c�digo
	// ALT+SHIFT+F no Eclipse permite corrigir identa��o
	
	// Para o unmarshalling ocorrer, obrigatoriamente devemos ter um 'construtor vazio' - no-argumento constructor.
	public Car() {

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


	public String getBrand() {
		return brand;
	}

	public String getColor() {
		return color;
	}

	public String getType() {
		return type;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Car [brand=" + brand + ", color=" + color + ", type=" + type + "]";
	}

}
