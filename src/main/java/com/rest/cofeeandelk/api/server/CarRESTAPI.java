package com.rest.cofeeandelk.api.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.cofeeandelk.entity.Car;
import com.rest.cofeeandelk.service.CarService;

// Para gerar um carro aleatório, precisamos de um @Service que irá usar a annotation @Autowired.
// Desta forma, Spring irá realizar a dependency injection.
// Como já setamos RandomCarService.java com a anotação @Service, Spring irá INJETAR uma INSTÂNCIA de RandomCarService a esta variável (carService).

@RequestMapping(value = "api/car/v1")
@RestController
public class CarRESTAPI {
	
	// Criando instância de RandomCarService para a variável carService através da anotação @Autowired
	@Autowired
	private CarService carService;
	
	// O método para retornar a response da request deve estar no controller - nesta classe, no caso.
	// POJO -> INTERFACE -> IMPLEMENTAÇÃO DA INTERFACE -> CONTROLLER
	
	
	// O método da resposta deve retornar o POJO. Uma instância de RandomCarService retirada de carService.
	
	@GetMapping(value = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
	public Car random() {
		
		return carService.generateCar();
	}

}
