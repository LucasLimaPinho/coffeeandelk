package com.rest.cofeeandelk.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Por simplicidade, para os campos "color" e "serialNumber" iremos dar default values, sem a necessidade de setá-los
// em RandomCarService como implementação da interface CarService. Estes campos são utilizados para testarmos a anotação
// @JsonIgnoreProperties

@JsonIgnoreProperties(value = {"color", "serialNumber"})
public class Engine {

	private String color = "Black";

	private String fuelType;
	
	private int horsePower;
	
	private String serialNumber = "X-COR";

	public Engine() {

	}

	public Engine(String fuelType, int horsePower) {
		super();
		this.fuelType = fuelType;
		this.horsePower = horsePower;
	}

	public Engine(String fuelType, int horsePower, String color, String serialNumber) {
		super();
		this.fuelType = fuelType;
		this.horsePower = horsePower;
		this.color = color;
		this.serialNumber = serialNumber;
	}

	public String getColor() {
		return color;
	}

	public String getFuelType() {
		return fuelType;
	}

	public int getHorsePower() {
		return horsePower;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public void setHorsePower(int horsePower) {
		this.horsePower = horsePower;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Override
	public String toString() {
		return "Engine [fuelType=" + fuelType + ", horsePower=" + horsePower + ", color=" + color + ", serialNumber="
				+ serialNumber + "]";
	}

}
