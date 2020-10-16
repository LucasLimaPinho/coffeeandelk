package com.rest.cofeeandelk.common;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.rest.cofeeandelk.entity.Car;
import com.rest.cofeeandelk.repository.CarElasticRepository;
import com.rest.cofeeandelk.service.CarService;


// Dummy data source para inserir 10.000 carros no Elasticsearch rodando em localhost:9200

@Component
public class CarElasticDatasource {

	private static final Logger LOG = LoggerFactory.getLogger(CarElasticDatasource.class);

	@Autowired
	private CarElasticRepository carRepository;

	@Autowired
	@Qualifier("randomCarService")
	private CarService carService;

	@Autowired
	@Qualifier("webClientElasticsearch")
	private WebClient webClient;

	// populateData() will run on application startup. Para fazer isso, usamos a anotação para fazer o listen
	// de ApplicationReadyEvent.class
	
	@EventListener(ApplicationReadyEvent.class)
	public void populateData() {
		
		// Iniciar deletando qualquer carro existente no Elasticsearch através de Spring Web Client.
		// Desta forma, sempre que iniciar a aplicação teremos "fresh" 10.000 cars.
		
		var response = webClient.delete().uri("/coffee-and-elk").retrieve().bodyToMono(String.class).block();
		LOG.info("End delete with response {}", response);

		var cars = new ArrayList<Car>();

		for (int i = 0; i < 10_000; i++) {
			cars.add(carService.generateCar());
		}

		// A interface carRepository salva a lista de random cars gerado por RandomCarService no Elasticsearch.
		
		carRepository.saveAll(cars);

		LOG.info("Saved {} cars", carRepository.count());
	}
}
