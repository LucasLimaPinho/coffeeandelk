package com.rest.cofeeandelk.api.server;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.rest.cofeeandelk.entity.Car;
import com.rest.cofeeandelk.service.CarService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CarRESTAPITest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testRandom() {
		
		webTestClient.get().uri("/api/car/v1/random").exchange().expectBody(Car.class)
		.value(car -> {
			assertTrue(CarService.BRANDS.contains(car.getBrand()));
			assertTrue(CarService.COLORS.contains(car.getColor()));
		});
		
	}

	@Test
	void testEcho() {
		fail("Not yet implemented");
	}

	@Test
	void testRandomCars() {
		fail("Not yet implemented");
	}

	@Test
	void testCount() {
		fail("Not yet implemented");
	}

	@Test
	void testSaveCar() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCar() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateCar() {
		fail("Not yet implemented");
	}

	@Test
	void testFindCarByBrandAndColor() {
		fail("Not yet implemented");
	}

	@Test
	void testFindCarByPath() {
		fail("Not yet implemented");
	}

	@Test
	void testFindCarByParams() {
		fail("Not yet implemented");
	}

	@Test
	void testFindCarReleaseAfter() {
		fail("Not yet implemented");
	}

}
