package com.rest.cofeeandelk.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.rest.cofeeandelk.entity.Car;

// Implementa��o da interface CarService.

// @Service annotation significa que esta classe � um bean que lida com Business logic.

@Service
public class RandomCarService implements CarService {
	
	// Esse m�todo deve gerar uma inst�ncia ALEAT�RIA do POJO Car.java e return it;
	// Apenas uma implementa��o da interface que criamos
	
	@Override
	public Car generateCar() {
		
		var brand = BRANDS.get(ThreadLocalRandom.current().nextInt(0, BRANDS.size()));
		var color = COLORS.get(ThreadLocalRandom.current().nextInt(0, COLORS.size()));
		var type = TYPES.get(ThreadLocalRandom.current().nextInt(0, TYPES.size()));
		return new Car(brand,color,type);
	}

}
