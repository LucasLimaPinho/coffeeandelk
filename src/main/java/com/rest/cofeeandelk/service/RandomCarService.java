package com.rest.cofeeandelk.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.rest.cofeeandelk.entity.Car;
import com.rest.cofeeandelk.util.RandomDateUtil;

// Implementação da interface CarService.

// @Service annotation significa que esta classe é um bean que lida com Business logic.

@Service
public class RandomCarService implements CarService {
	
	// Esse método deve gerar uma instância ALEATÓRIA do POJO Car.java e return it;
	// Apenas uma implementação da interface que criamos
	
	@Override
	public Car generateCar() {
		
		var brand = BRANDS.get(ThreadLocalRandom.current().nextInt(0, BRANDS.size()));
		var color = COLORS.get(ThreadLocalRandom.current().nextInt(0, COLORS.size()));
		var type = TYPES.get(ThreadLocalRandom.current().nextInt(0, TYPES.size()));
		var available = ThreadLocalRandom.current().nextBoolean();
		var price = ThreadLocalRandom.current().nextInt(5000, 120001);
		var firstReleaseDate = RandomDateUtil.generateRandomLocalDate();
		var result = new Car(brand, color, type);
		result.setAvailable(available);
		result.setPrice(price);
		result.setFirstReleaseDate(firstReleaseDate);
				
		return result;
	}

}
