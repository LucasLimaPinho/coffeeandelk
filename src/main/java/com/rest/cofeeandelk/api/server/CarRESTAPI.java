package com.rest.cofeeandelk.api.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	// Criando inst�ncia de RandomCarService para a vari�vel carService atrav�s da anota��o @Autowired
	@Autowired
	private CarService carService;
	
	// O m�todo para retornar a response da request deve estar no controller - nesta classe, no caso.
	// POJO -> INTERFACE -> IMPLEMENTA��O DA INTERFACE -> CONTROLLER
	
	
	// O m�todo da resposta deve retornar o POJO. Uma inst�ncia de RandomCarService retirada de carService.
	
	@GetMapping(value = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
	public Car random() {
		
		return carService.generateCar();
	}

}
