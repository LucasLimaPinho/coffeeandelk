package com.rest.cofeeandelk.service;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.rest.cofeeandelk.entity.Car;
import com.rest.cofeeandelk.entity.Engine;
import com.rest.cofeeandelk.entity.Tire;
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

		var tires = new ArrayList<Tire>();
		for (int i = 0; i < 3; i++) {
			var tire = new Tire();
			var manufacturer = TIRE_MANUFACTURER.get(ThreadLocalRandom.current().nextInt(TIRE_MANUFACTURER.size()));
			var size = ThreadLocalRandom.current().nextInt(15, 18);
			var tirePrice = ThreadLocalRandom.current().nextInt(200, 401);
			tire.setManufacturer(manufacturer);
			tire.setPrice(tirePrice);
			tire.setSize(size);

			tires.add(tire);

		}
		var result = new Car(brand, color, type);
		// Random count between zero and list size
		int randomCount = ThreadLocalRandom.current().nextInt(ADDITIONAL_FEATURES.size());
		var additionalFeatures = new ArrayList<String>();
		for (int i = 0; i < randomCount; i++) {
			additionalFeatures.add(ADDITIONAL_FEATURES.get(i));
		}

		var fuel = FUELS.get(ThreadLocalRandom.current().nextInt(FUELS.size()));
		var horsePower = ThreadLocalRandom.current().nextInt(100, 221);
		var engine = new Engine();

		engine.setFuelType(fuel);
		engine.setHorsePower(horsePower);

		result.setAvailable(available);
		result.setPrice(price);
		result.setFirstReleaseDate(firstReleaseDate);
		result.setAdditionalFeatures(additionalFeatures);
		result.setEngine(engine);
		result.setTires(tires);

		return result;
	}

}
