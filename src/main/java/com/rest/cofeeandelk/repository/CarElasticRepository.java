package com.rest.cofeeandelk.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.rest.cofeeandelk.entity.Car;

// @Repository: interface para acessar e modificar dados no Elasticsearch.
// A interface básica já possui CRUD operations. Com esta interface temos um repository para criar, fazer update, etc.

// A primeira classe de ElasticsearchRepository deve ser a classe a ser armazenada em data storage do Elasticsearch!!!

// A segunda é o field identificador de Car, então usaremos String.

@Repository
public interface CarElasticRepository extends ElasticsearchRepository<Car, String> {
	
	public List<Car> findByBrandAndColor(String brand, String color);
	

}
