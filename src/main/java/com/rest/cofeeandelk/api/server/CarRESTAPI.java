package com.rest.cofeeandelk.api.server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.cofeeandelk.entity.Car;
import com.rest.cofeeandelk.service.CarService;

// Para gerar um carro aleat�rio, precisamos de um @Service que ir� usar a annotation @Autowired.
// Desta forma, Spring ir� realizar a dependency injection.
// Como j� setamos RandomCarService.java com a anota��o @Service, Spring ir� INJETAR uma INST�NCIA de RandomCarService a esta vari�vel (carService).

@RequestMapping(value = "api/car/v1")
@RestController
public class CarRESTAPI {

	private static final Logger LOG = LoggerFactory.getLogger(CarRESTAPI.class);

	// Criando inst�ncia de RandomCarService para a vari�vel carService atrav�s da
	// anota��o @Autowired
	@Autowired
	private CarService carService;

	// O m�todo para retornar a response da request deve estar no controller - nesta
	// classe, no caso.
	// POJO -> INTERFACE -> IMPLEMENTA��O DA INTERFACE -> CONTROLLER

	// O m�todo da resposta deve retornar o POJO. Uma inst�ncia de RandomCarService
	// retirada de carService.

	@GetMapping(value = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
	public Car random() {

		return carService.generateCar();
	}

	// Para o m�todo POST, iremos receber um JSON como request body e, no caso,
	// retornar uma string
	// comprovando que conseguimos fazer o processo deserialization/unmarshalling
	// embedded no Spring Boot Framework
	// Iremos transformar o request body em JSON em um POJO e retornar
	// Car.toString();
	// Precisamos tamb�m aqui da annotation @RequestBody para capturar o JSON de
	// request.

	@PostMapping(value = "/echo", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String echo(@RequestBody Car car) {

		LOG.info("Car is {}", car);
		return car.toString();

	}

	@GetMapping(value = "/random-cars", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Car> randomCars() {

		var result = new ArrayList<Car>();
		for (int i = 0; i < ThreadLocalRandom.current().nextInt(1, 10); i++) {
			result.add(carService.generateCar());
		}

		return result;
	}

}
