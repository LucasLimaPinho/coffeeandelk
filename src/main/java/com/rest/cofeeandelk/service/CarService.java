package com.rest.cofeeandelk.service;

import java.util.List;

import com.rest.cofeeandelk.entity.Car;

// CarService � uma interface onde iremos definir a business logic - que n�o pode estar inserida no POJO
// para o Spring Boot Framework. Basicamente, CarService ir� conter os m�todos que devem ser chamados pelo controller
// para dar a resposta ao cliente.

// Service ----> Business logic
// POJO ----> No business logic

// O Service deve estar separado da entidade (POJO)

public interface CarService {
	
	List<String> BRANDS = List.of("Toyota", "Honda", "Ford");
	
	List<String> COLORS = List.of("Red","Black", "White");
	
	List<String> TYPES = List.of("Sedan","SUV","MPV");
	
	List<String> ADDITIONAL_FEATURES = List.of("GPS","alarm","sunroof","media player", "Leather seats");
	
	Car generateCar();
}
