package com.rest.cofeeandelk.api.server;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.cofeeandelk.api.response.ErrorResponse;
import com.rest.cofeeandelk.entity.Car;
import com.rest.cofeeandelk.repository.CarElasticRepository;
import com.rest.cofeeandelk.service.CarService;

// Para gerar um carro aleatório, precisamos de um @Service que irá usar a annotation @Autowired.
// Desta forma, Spring irá realizar a dependency injection.
// Como já setamos RandomCarService.java com a anotação @Service, Spring irá INJETAR uma INSTÂNCIA de RandomCarService a esta variável (carService).

@RequestMapping(value = "api/car/v1")
@RestController
public class CarRESTAPI {

	private static final Logger LOG = LoggerFactory.getLogger(CarRESTAPI.class);

	@Autowired
	private CarElasticRepository carElasticRepository;

	// Criando instância de RandomCarService para a variável carService através da
	// anotação @Autowired
	@Autowired
	private CarService carService;

	// O método para retornar a response da request deve estar no controller - nesta
	// classe, no caso.
	// POJO -> INTERFACE -> IMPLEMENTAÇÃO DA INTERFACE -> CONTROLLER

	// O método da resposta deve retornar o POJO. Uma instância de RandomCarService
	// retirada de carService.

	@GetMapping(value = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
	public Car random() {

		return carService.generateCar();
	}

	// Para o método POST, iremos receber um JSON como request body e, no caso,
	// retornar uma string
	// comprovando que conseguimos fazer o processo deserialization/unmarshalling
	// embedded no Spring Boot Framework
	// Iremos transformar o request body em JSON em um POJO e retornar
	// Car.toString();
	// Precisamos também aqui da annotation @RequestBody para capturar o JSON de
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

	@GetMapping(value = "/count")
	public String count() {
		return "There are : " + carElasticRepository.count() + " cars";

	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String saveCar(@RequestBody Car car) {

		var id = carElasticRepository.save(car).getId();

		return "Saved with ID : " + id;

	}

	@GetMapping(value = "/{id}")
	public Car getCar(@PathVariable("id") String carId) {

		return carElasticRepository.findById(carId).orElse(null);

	}

	@PutMapping(value = "/{id}")
	public String updateCar(@PathVariable("id") String carId, @RequestBody Car updatedCar) {

		updatedCar.setId(carId);
		var newCar = carElasticRepository.save(updatedCar);

		return "Updated car : " + newCar.getId();
	}

	@GetMapping(value = "find-json", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Car> findCarByBrandAndColor(@RequestBody Car car, @RequestParam(defaultValue = "10") int page,
			@RequestParam(defaultValue = "0") int size) {

		var pageable = PageRequest.of(page, size, Sort.by(Direction.DESC, "price"));

		return carElasticRepository.findByBrandAndColor(car.getBrand(), car.getColor(), pageable).getContent();

	}

	// Query Lista de Carros do ElasticSearch por PATH PARAMETERS na URL;

	@GetMapping(value = "/cars/{brand}/{color}")
	public ResponseEntity<Object> findCarByPath(@PathVariable("brand") String brand,
			@PathVariable("color") String color, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		// Vamos usar aqui StringUtil para checar se o campo {color} provided pelo user
		// contém numérico
		// Caso sim, devemos retornar a classe ErrorResponse.class com messagem de erro
		// e timestamp do erro. Quando o erro ocorre pelo lado do cliente, devemos responder ResponseEntity
		// O HTTP status precisa ser 400 - BadRequest
		
		var headers = new HttpHeaders();
		headers.add(HttpHeaders.SERVER, "Spring");
		headers.add("X-Custom-Header", "Custom Response Header");
		
		if (StringUtils.isNumeric(color)) {
			
			var errorResponse = new ErrorResponse("Invalid color : " + color, LocalDateTime.now());
			
			// ResponseEntity tem muitos construtores. Iremos basicamente utilizar o construtor com os argumentos
			// body, http header e http status code.
			
			// Body: objeto da classe ErrorResponse.class
			// Http Header: null
			// HttpStatusCode: 400 - Bad Request
			
			return new ResponseEntity<Object>(errorResponse, headers, HttpStatus.BAD_REQUEST);
		}		

		var pageable = PageRequest.of(page, size, Sort.by(Direction.DESC, "price"));
		
		// Aqui precisamos retornar List<Car> com HTTP_STATUS_CODE = 200 OK
		// Vamos usar um builder da classe ResponseEntity
		
		var cars = carElasticRepository.findByBrandAndColor(brand, color, pageable).getContent();
		
		return ResponseEntity.ok().headers(headers).body(cars);
	}

	// Query Lista de Carros do ElasticSearch por QUERY PARAMETERS na URL;

	@GetMapping(value = "/cars")
	public List<Car> findCarByParams(@RequestParam String brand, @RequestParam String color,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		var pageable = PageRequest.of(page, size, Sort.by(Direction.DESC, "price"));
		return carElasticRepository.findByBrandAndColor(brand, color, pageable).getContent();

	}

	@GetMapping(value = "/cars/date")
	public List<Car> findCarReleaseAfter(
			@RequestParam(name = "first_release_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate firstReleaseDate) {

		return carElasticRepository.findByFirstReleaseDateAfter(firstReleaseDate);

	}
}
