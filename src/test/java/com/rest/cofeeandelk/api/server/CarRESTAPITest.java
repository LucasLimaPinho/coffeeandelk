package com.rest.cofeeandelk.api.server;

import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.rest.cofeeandelk.entity.Car;
import com.rest.cofeeandelk.service.CarService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CarRESTAPITest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testRandom() {

		for (int i = 0; i < 100; i++) {
			System.out.println("Testing loop :" + i);
			webTestClient.get().uri("/api/car/v1/random").exchange().expectBody(Car.class).value(car -> {
				assertTrue(CarService.BRANDS.contains(car.getBrand()));
				assertTrue(CarService.COLORS.contains(car.getColor()));
			});

		}

	}

	// We inject a CarService bean into the carService field.
	// The @Qualifier("randomCarService") specifies that it is a RandomCarService
	// bean.
	// CarService is an interface; RandomCarService implements CarService;
	// So we can have multiple beans implementing the interface CarService;
	// The annotation @Qualifier will uniquely specify what type of bean to be
	// inject with loose-coupling

	@Autowired
	@Qualifier("randomCarService")
	private CarService carService;

	@Test
	void testSaveCar() {

		var randomCar = carService.generateCar();

		// Assert that the HTTP POST Request will last less than 1 second

		for (int i = 0; i < 100; i++) {

			System.out.println("Inserting " + i + " document in Elasticsearch");
			assertTimeout(Duration.ofSeconds(1),
					() -> webTestClient.post().uri("api/car/v1").contentType(MediaType.APPLICATION_JSON)
							.bodyValue(randomCar).exchange().expectStatus().is2xxSuccessful());

		}

	}

	@Test
	void testFindCarByParams() {

		final int size = 5;

		for (var brand : CarService.BRANDS) {
			for (var color : CarService.COLORS) {

				webTestClient.get()
						.uri(uriBuilder -> uriBuilder.path("/api/car/v1/cars").queryParam("brand", brand)
								.queryParam("color", color).queryParam("size", size).queryParam("page", 0).build())
						.exchange().expectBodyList(Car.class).value(cars -> {

							assertNotNull(cars);
							assertTrue(cars.size() <= size);

						});
			}
		}
	}

}
